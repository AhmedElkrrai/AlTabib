package com.example.altabib.featuers.favorites.domain

import com.example.altabib.featuers.dashboard.domain.entities.Doctor

interface FavoritesRepository {
    suspend fun isFavorite(doctorId: String): Boolean
    suspend fun getFavorites(): List<Doctor>
    suspend fun addFavorite(doctor: Doctor)
    suspend fun removeFavorite(doctor: Doctor)
}
