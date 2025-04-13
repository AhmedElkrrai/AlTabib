package com.example.altabib.featuers.favorites.domain.usecases

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.featuers.favorites.domain.FavoritesRepository

class RemoveFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(doctor: Doctor): Result<Unit, DataError> {
        return repository.removeFavorite(doctor)
    }
}
