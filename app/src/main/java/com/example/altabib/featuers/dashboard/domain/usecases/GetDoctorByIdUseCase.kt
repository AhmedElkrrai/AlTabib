package com.example.altabib.featuers.dashboard.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.altabib.featuers.dashboard.domain.DoctorRepository
import com.example.user.domain.entities.Doctor

class GetDoctorByIdUseCase(
    private val repository: DoctorRepository
) {
    suspend operator fun invoke(doctorId: String): Result<Doctor, DataError> {
        return repository.getDoctorById(doctorId)
    }
}
