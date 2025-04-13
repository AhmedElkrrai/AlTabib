package com.example.altabib.featuers.favorites.domain

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.dashboard.domain.entities.Doctor

interface FavoritesRepository {
    suspend fun isFavorite(doctorId: String): Boolean
    suspend fun getFavorites(): Result<List<Doctor>, DataError>
    suspend fun addFavorite(doctor: Doctor): Result<Unit, DataError>
    suspend fun removeFavorite(doctor: Doctor): Result<Unit, DataError>
}
