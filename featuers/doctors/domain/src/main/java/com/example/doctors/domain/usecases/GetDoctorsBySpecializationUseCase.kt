package com.example.doctors.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.doctors.domain.DoctorRepository
import com.example.user.domain.entities.Doctor
import com.example.user.domain.usecases.GetUserUseCase

class GetDoctorsBySpecializationUseCase(
    private val repository: DoctorRepository,
    private val getUserUseCase: GetUserUseCase,
) {
    suspend operator fun invoke(key: String): Result<List<Doctor>, DataError> {
        val user = getUserUseCase.invoke()
        val city = user?.city ?: return Result.Error(DataError.GeneralError)
        return repository.getDoctorsBySpecialization(key, city)
    }
}
