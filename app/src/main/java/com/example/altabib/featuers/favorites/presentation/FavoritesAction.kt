package com.example.altabib.featuers.favorites.presentation

import com.example.user.domain.entities.Doctor

sealed class FavoritesAction {
    data object LoadFavorites : FavoritesAction()
    data class OnDoctorClick(val doctorId: String) : FavoritesAction()
    data class UnFavoriteDoctor(val doctor: Doctor) : FavoritesAction()
}