package com.example.favorites.domain.usecases

import com.example.doctors.domain.DoctorRepository

class IsFavoriteUseCase(
    private val repository: DoctorRepository
) {
    suspend operator fun invoke(doctorId: String): Boolean {
        return repository.isFavorite(doctorId)
    }
}
