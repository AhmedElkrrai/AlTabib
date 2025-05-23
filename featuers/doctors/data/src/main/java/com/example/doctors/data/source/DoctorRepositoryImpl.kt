package com.example.doctors.data.source

import android.util.Log
import com.example.altabib.core.DOCTORS_PATH
import com.example.altabib.core.DataError
import com.example.altabib.core.LocalImageStorage
import com.example.altabib.core.Result
import com.example.doctors.data.source.local.DoctorDao
import com.example.doctors.data.source.local.mappers.toDomain
import com.example.doctors.data.source.local.mappers.toEntity
import com.example.doctors.data.source.remote.mappers.toDomain
import com.example.doctors.data.source.remote.mappers.toDto
import com.example.doctors.data.source.remote.models.DoctorDto
import com.example.doctors.domain.DoctorRepository
import com.example.user.domain.entities.Availability
import com.example.user.domain.entities.Doctor
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

private const val CITY_FIELD = "city"
private const val AVATARS = "avatars"
private const val AVATAR_FIELD = "avatar"
private const val REVIEWS_FIELD = "reviews"
private const val AVAILABILITY_FIELD = "availability"
private const val SPECIALIZATION_FIELD = "specialization"

class DoctorRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage,
    private val localImageStorage: LocalImageStorage,
    private val dao: DoctorDao
) : DoctorRepository {

    override suspend fun searchDoctors(
        city: String,
        query: String
    ): Result<List<Doctor>, DataError> {
        return try {
            val doctorsSnapshot = firestore.collection(DOCTORS_PATH)
                .whereEqualTo(CITY_FIELD, city)
                .get()
                .await()

            val allDoctors = doctorsSnapshot.toObjects(DoctorDto::class.java)
            val filtered = allDoctors
                .filter {
                    it.name.contains(query, ignoreCase = true) || it.specialization.contains(
                        query,
                        ignoreCase = true
                    )
                }.map { it.toDomain() }

            Result.Success(filtered)
        } catch (e: Exception) {
            Log.e("DoctorRepo", "Error in searchDoctors", e)
            Result.Error(DataError.FailedToRetrieveData)
        }
    }

    override suspend fun getDoctorById(doctorId: String): Result<Doctor, DataError> {
        return try {
            val snapshot = firestore.collection(DOCTORS_PATH)
                .document(doctorId)
                .get()
                .await()

            val dto = snapshot.toObject(DoctorDto::class.java)
            if (dto != null) {
                Result.Success(dto.toDomain())
            } else {
                Result.Error(DataError.FailedToRetrieveData)
            }
        } catch (e: Exception) {
            Log.e("DoctorRepo", "Error in getDoctorById", e)
            Result.Error(DataError.FailedToRetrieveData)
        }
    }

    override suspend fun getDoctorsBySpecialization(
        specialization: String,
        city: String
    ): Result<List<Doctor>, DataError> {
        return try {
            // 1. Fetch remote doctors from Firestore
            val snapshot = firestore.collection(DOCTORS_PATH)
                .whereEqualTo(CITY_FIELD, city)
                .whereEqualTo(SPECIALIZATION_FIELD, specialization)
                .orderBy(REVIEWS_FIELD, Query.Direction.DESCENDING)
                .get()
                .await()

            val remoteDoctors = snapshot.toObjects(DoctorDto::class.java).map { it.toDomain() }

            // 2. Get currently favorite doctor IDs from local DB
            val favoriteIds = dao.getFavoriteDoctorIds().toSet()

            // 3. Map doctors to entities, preserving favorite flag
            val doctorEntities = remoteDoctors.map { domain ->
                val favorite = domain.id in favoriteIds
                domain.toEntity(favorite).copy(isFavorite = favorite)
            }

            // 4. Cache updated list to Room
            dao.insertDoctors(doctorEntities)

            Result.Success(remoteDoctors)
        } catch (e: Exception) {
            Log.e("DoctorRepo", "Error in getDoctorsBySpecialization", e)

            // 5. Fallback to cached doctors from Room
            val cached = dao.getDoctorsBySpecializationAndCity(specialization, city)
            return if (cached.isNotEmpty()) {
                Result.Success(cached.map { it.toDomain() })
            } else {
                Result.Error(DataError.FailedToRetrieveData)
            }
        }
    }

    override suspend fun upsertDoctor(doctor: Doctor): Result<Unit, DataError> {
        return try {
            val dto = doctor.toDto()
            firestore.collection(DOCTORS_PATH)
                .document(dto.id)
                .set(dto)
                .await()

            Result.Success(Unit)
        } catch (e: Exception) {
            Log.e("DoctorRepo", "Error in upsertDoctor", e)
            Result.Error(DataError.FailedToUpdateData)
        }
    }

    override suspend fun isFavorite(doctorId: String): Boolean {
        return dao.getFavoriteDoctorIds().any { it == doctorId }
    }

    override suspend fun getFavorites(): Result<List<Doctor>, DataError> {
        return try {
            val favorites = dao.getFavorites().map { it.toDomain() }
            Result.Success(favorites)
        } catch (e: Exception) {
            Result.Error(DataError.LocalError)
        }
    }

    override suspend fun addFavorite(doctor: Doctor): Result<Unit, DataError> {
        return try {
            dao.updateFavoriteStatus(doctor.id, true)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.LocalError)
        }
    }

    override suspend fun removeFavorite(doctor: Doctor): Result<Unit, DataError> {
        return try {
            dao.updateFavoriteStatus(doctor.id, false)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.LocalError)
        }
    }

    override suspend fun uploadAvatar(
        userId: String,
        bytes: ByteArray
    ): Result<String, DataError> {
        return withContext(Dispatchers.IO) {
            try {
                // 1. Upload avatar to Firebase Storage
                val ref = storage.reference.child("$AVATARS/$userId.jpg")
                ref.putBytes(bytes).await()

                // 2. Get download URL
                val url = ref.downloadUrl.await().toString()

                // 3. Update Firestore doctor document with the new avatar URL
                firestore.collection(DOCTORS_PATH)
                    .document(userId)
                    .update(AVATAR_FIELD, url)
                    .await()

                Result.Success(url)
            } catch (e: Exception) {
                Log.e("DoctorRepo", "Error in uploadAvatar", e)
                Result.Error(DataError.FailedToUpdateData)
            }
        }
    }

    override suspend fun cacheAvatar(
        userId: String,
        bytes: ByteArray,
        path: String?
    ): Result<String, DataError> {
        return withContext(Dispatchers.IO) {
            try {
                val uri = localImageStorage.saveAvatar(userId, bytes)
                if (path != null && localImageStorage.isLocalAvatar(path)) {
                    localImageStorage.deleteAvatar(userId)
                }
                Result.Success(uri)
            } catch (e: Exception) {
                Log.e("DoctorRepo", "Local image save failed", e)
                Result.Error(DataError.FailedToUpdateData)
            }
        }
    }

    override suspend fun updateAvailability(
        userId: String,
        availability: Availability
    ): Result<Unit, DataError> {
        return try {
            firestore.collection(DOCTORS_PATH)
                .document(userId)
                .update(AVAILABILITY_FIELD, availability.toDto())
                .await()

            Result.Success(Unit)
        } catch (e: Exception) {
            Log.e("DoctorRepo", "Error in updateAvailability", e)
            Result.Error(DataError.FailedToUpdateData)
        }
    }
}
