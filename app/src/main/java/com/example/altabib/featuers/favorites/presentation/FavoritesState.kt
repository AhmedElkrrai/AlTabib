package com.example.altabib.featuers.favorites.presentation

import com.example.altabib.featuers.dashboard.domain.entities.Doctor

data class FavoritesState(
    val isLoading: Boolean = false,
    val favorites: List<Doctor> = emptyList()
)