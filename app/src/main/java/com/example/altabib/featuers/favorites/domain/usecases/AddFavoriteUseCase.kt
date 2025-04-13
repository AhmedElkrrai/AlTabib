package com.example.altabib.featuers.favorites.domain.usecases

import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.featuers.favorites.domain.FavoritesRepository

class AddFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(doctor: Doctor) {
        repository.addFavorite(doctor)
    }
}
