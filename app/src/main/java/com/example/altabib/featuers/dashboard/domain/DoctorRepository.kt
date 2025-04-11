package com.example.altabib.featuers.dashboard.domain

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.core.domain.util.Result

interface DoctorRepository {
    suspend fun searchDoctors(city: String, query: String): Result<List<Doctor>, DataError>
    suspend fun getDoctorById(doctorId: String): Result<Doctor, DataError>
    suspend fun addDoctor(doctor: Doctor): Result<Unit, DataError>
    suspend fun updateDoctor(doctor: Doctor): Result<Unit, DataError>
    suspend fun getDoctorsBySpecialization(
        specialization: String,
        city: String
    ): Result<List<Doctor>, DataError>
}
