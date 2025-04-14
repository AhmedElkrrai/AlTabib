package com.example.altabib.featuers.favorites.presentation

sealed class FavoritesAction {
    data object LoadFavorites : FavoritesAction()
    data class OnDoctorClick(val doctorId: String) : FavoritesAction()
}