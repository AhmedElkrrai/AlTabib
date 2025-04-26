package com.example.altabib.featuers.dashboard.domain

import com.example.altabib.core.DataError
import com.example.user.domain.entities.Doctor
import com.example.altabib.core.Result

interface DoctorRepository {
    suspend fun searchDoctors(city: String, query: String): Result<List<Doctor>, DataError>
    suspend fun getDoctorById(doctorId: String): Result<Doctor, DataError>
    suspend fun addDoctor(doctor: Doctor): Result<Unit, DataError>
    suspend fun updateDoctor(doctor: Doctor): Result<Unit, DataError>
    suspend fun getDoctorsBySpecialization(
        specialization: String,
        city: String
    ): Result<List<Doctor>, DataError>

    suspend fun isFavorite(doctorId: String): Boolean
    suspend fun getFavorites(): Result<List<Doctor>, DataError>
    suspend fun addFavorite(doctor: Doctor): Result<Unit, DataError>
    suspend fun removeFavorite(doctor: Doctor): Result<Unit, DataError>
}
