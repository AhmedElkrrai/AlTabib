package com.example.altabib.featuers.favorites.domain.usecases

import com.example.altabib.featuers.dashboard.domain.DoctorRepository

class IsFavoriteUseCase(
    private val repository: DoctorRepository
) {
    suspend operator fun invoke(doctorId: String): Boolean {
        return repository.isFavorite(doctorId)
    }
}
