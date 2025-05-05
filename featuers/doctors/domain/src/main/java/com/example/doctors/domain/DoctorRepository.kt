package com.example.doctors.domain

import com.example.altabib.core.DataError
import com.example.user.domain.entities.Doctor
import com.example.altabib.core.Result
import com.example.user.domain.entities.Availability

interface DoctorRepository {
    suspend fun searchDoctors(city: String, query: String): Result<List<Doctor>, DataError>
    suspend fun getDoctorById(doctorId: String): Result<Doctor, DataError>
    suspend fun upsertDoctor(doctor: Doctor): Result<Unit, DataError>
    suspend fun getDoctorsBySpecialization(
        specialization: String,
        city: String
    ): Result<List<Doctor>, DataError>

    suspend fun isFavorite(doctorId: String): Boolean
    suspend fun getFavorites(): Result<List<Doctor>, DataError>
    suspend fun addFavorite(doctor: Doctor): Result<Unit, DataError>
    suspend fun removeFavorite(doctor: Doctor): Result<Unit, DataError>
    suspend fun uploadAvatar(userId: String, bytes: ByteArray): Result<String, DataError>
    suspend fun cacheAvatar(
        userId: String,
        bytes: ByteArray,
        path: String?
    ): Result<String, DataError>

    suspend fun updateAvailability(
        userId: String,
        availability: Availability
    ): Result<Unit, DataError>
}
