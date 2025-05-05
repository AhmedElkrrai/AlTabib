package com.example.doctors.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.doctors.domain.DoctorRepository
import com.example.user.domain.entities.Availability
import com.example.user.domain.usecases.GetUserUseCase

class UpdateAvailabilityUseCase(
    private val repository: DoctorRepository,
    private val getUserUseCase: GetUserUseCase
) {
    suspend operator fun invoke(availability: Availability): Result<Unit, DataError> {
        val userId = getUserUseCase()?.uid ?: return Result.Error(DataError.LocalError)
        return repository.updateAvailability(userId, availability)
    }
}
