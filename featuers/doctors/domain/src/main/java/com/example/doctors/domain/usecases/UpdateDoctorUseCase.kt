package com.example.doctors.domain.usecases

import com.example.altabib.core.DataError
import com.example.doctors.domain.DoctorRepository
import com.example.user.domain.entities.Doctor
import com.example.altabib.core.Result

class UpdateDoctorUseCase(
    private val repository: DoctorRepository
) {
    suspend operator fun invoke(doctor: Doctor): Result<Unit, DataError> {
        return repository.updateDoctor(doctor)
    }
}
