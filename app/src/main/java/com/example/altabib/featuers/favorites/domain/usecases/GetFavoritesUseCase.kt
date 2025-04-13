package com.example.altabib.featuers.favorites.domain.usecases

import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.featuers.favorites.domain.FavoritesRepository

class GetFavoritesUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(): List<Doctor> {
        return repository.getFavorites()
    }
}
