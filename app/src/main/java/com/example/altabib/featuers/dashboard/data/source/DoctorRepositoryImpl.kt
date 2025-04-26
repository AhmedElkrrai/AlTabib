package com.example.altabib.featuers.dashboard.data.source

import android.util.Log
import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.altabib.featuers.dashboard.data.source.local.DoctorDao
import com.example.altabib.featuers.dashboard.data.source.local.mappers.toDomain
import com.example.altabib.featuers.dashboard.data.source.local.mappers.toEntity
import com.example.altabib.featuers.dashboard.data.source.remote.mappers.toDomain
import com.example.altabib.featuers.dashboard.data.source.remote.mappers.toDto
import com.example.altabib.featuers.dashboard.data.source.remote.models.DoctorDto
import com.example.altabib.featuers.dashboard.domain.DoctorRepository
import com.example.user.data.source.remote.DOCTORS_PATH
import com.example.user.domain.entities.Doctor
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

private const val CITY_FIELD = "city"
private const val REVIEWS_FIELD = "reviews"
private const val SPECIALIZATION_FIELD = "specialization"

class DoctorRepositoryImpl(
    private val firestore: FirebaseFirestore,
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
            val filtered = allDoctors.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.specialization.contains(query, ignoreCase = true)
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

    override suspend fun updateDoctor(doctor: Doctor): Result<Unit, DataError> {
        return try {
            val dto = doctor.toDto()
            firestore.collection(DOCTORS_PATH)
                .document(dto.id)
                .set(dto)
                .await()

            Result.Success(Unit)
        } catch (e: Exception) {
            Log.e("DoctorRepo", "Error in updateDoctor", e)
            Result.Error(DataError.FailedToUpdateData)
        }
    }

    override suspend fun addDoctor(doctor: Doctor): Result<Unit, DataError> {
        return try {
            val dto = doctor.toDto()
            firestore.collection(DOCTORS_PATH)
                .document(dto.id)
                .set(dto)
                .await()
            Result.Success(Unit)
        } catch (e: Exception) {
            Log.e("DoctorRepo", "Error in addDoctor", e)
            Result.Error(DataError.GeneralError)
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
}
