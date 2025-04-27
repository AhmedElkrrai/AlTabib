package com.example.favorites.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.doctors.domain.DoctorRepository
import com.example.user.domain.entities.Doctor

class AddFavoriteUseCase(
    private val repository: DoctorRepository
) {
    suspend operator fun invoke(doctor: Doctor): Result<Unit, DataError> {
        return repository.addFavorite(doctor)
    }
}
