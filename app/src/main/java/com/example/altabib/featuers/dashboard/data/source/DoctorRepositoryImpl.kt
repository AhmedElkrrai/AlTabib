package com.example.altabib.featuers.dashboard.data.source

import android.util.Log
import com.example.altabib.core.domain.util.DataError
import com.example.altabib.featuers.dashboard.domain.DoctorRepository
import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.google.firebase.firestore.FirebaseFirestore
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.dashboard.data.source.remote.mappers.toDomain
import com.example.altabib.featuers.dashboard.data.source.remote.mappers.toDto
import com.example.altabib.featuers.dashboard.data.source.remote.models.DoctorDto
import com.example.altabib.navigation.utils.NAME
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

private const val DOCTORS_PATH = "doctors"
private const val CITY_FIELD = "city"
private const val REVIEWS_FIELD = "reviews"
private const val SPECIALIZATION_FIELD = "specialization"

class DoctorRepositoryImpl(
    private val firestore: FirebaseFirestore
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
            Result.Error(DataError.RetrievalError(e.message ?: "Could not search doctors"))
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
                Result.Error(DataError.RetrievalError("Doctor not found"))
            }
        } catch (e: Exception) {
            Log.e("DoctorRepo", "Error in getDoctorById", e)
            Result.Error(DataError.RetrievalError(e.message ?: "Could not get doctor"))
        }
    }

    override suspend fun getDoctorsBySpecialization(
        specialization: String,
        city: String
    ): Result<List<Doctor>, DataError> {
        return try {
            val snapshot = firestore.collection(DOCTORS_PATH)
                .whereEqualTo(CITY_FIELD, city)
                .whereEqualTo(SPECIALIZATION_FIELD, specialization)
                .orderBy(REVIEWS_FIELD, Query.Direction.DESCENDING)
                .get()
                .await()

            val doctors = snapshot.toObjects(DoctorDto::class.java).map { it.toDomain() }
            Result.Success(doctors)
        } catch (e: Exception) {
            Log.e("DoctorRepo", "Error in getDoctorsBySpecialization", e)
            Result.Error(
                DataError.RetrievalError(
                    e.message ?: "Could not fetch doctors"
                )
            )
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
            Result.Error(DataError.WriteError(e.message ?: "Could not add doctor"))
        }
    }
}
