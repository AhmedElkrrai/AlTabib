package com.example.altabib.featuers.favorites.presentation

import com.example.altabib.featuers.user.domain.entities.Doctor

data class FavoritesState(
    val isLoading: Boolean = false,
    val doctors: List<Doctor> = emptyList()
)