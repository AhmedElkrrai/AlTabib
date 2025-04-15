package com.example.altabib.featuers.favorites.domain.usecases

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.dashboard.domain.DoctorRepository
import com.example.altabib.featuers.dashboard.domain.entities.Doctor

class GetFavoritesUseCase(
    private val repository: DoctorRepository
) {
    suspend operator fun invoke(): Result<List<Doctor>, DataError> {
        return repository.getFavorites()
    }
}
