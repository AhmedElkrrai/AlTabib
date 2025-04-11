package com.example.altabib.featuers.dashboard.domain.usecases

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.dashboard.domain.DoctorRepository
import com.example.altabib.featuers.dashboard.domain.entities.Doctor

class GetDoctorByIdUseCase(
    private val repository: DoctorRepository
) {
    suspend operator fun invoke(doctorId: String): Result<Doctor, DataError> {
        return repository.getDoctorById(doctorId)
    }
}
