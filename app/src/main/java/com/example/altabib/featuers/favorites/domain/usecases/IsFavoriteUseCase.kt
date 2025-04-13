package com.example.altabib.featuers.favorites.domain.usecases

import com.example.altabib.featuers.favorites.domain.FavoritesRepository

class IsFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(doctorId: String): Boolean {
        return repository.isFavorite(doctorId)
    }
}
