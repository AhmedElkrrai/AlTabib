package com.example.favorites.presentation

import com.example.user.domain.entities.Doctor

data class FavoritesState(
    val isLoading: Boolean = false,
    val doctors: List<Doctor> = emptyList()
)