package com.example.altabib.featuers.favorites.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.altabib.featuers.dashboard.domain.DoctorRepository
import com.example.altabib.featuers.dashboard.domain.entities.Doctor

class AddFavoriteUseCase(
    private val repository: DoctorRepository
) {
    suspend operator fun invoke(doctor: Doctor): Result<Unit, DataError> {
        return repository.addFavorite(doctor)
    }
}
