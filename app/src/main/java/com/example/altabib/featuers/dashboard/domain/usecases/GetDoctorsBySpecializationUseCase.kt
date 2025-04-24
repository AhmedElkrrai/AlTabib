package com.example.altabib.featuers.dashboard.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.featuers.user.domain.entities.Doctor
import com.example.altabib.core.Result
import com.example.altabib.featuers.dashboard.domain.DoctorRepository
import com.example.altabib.featuers.user.domain.usecases.GetUserUseCase

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
