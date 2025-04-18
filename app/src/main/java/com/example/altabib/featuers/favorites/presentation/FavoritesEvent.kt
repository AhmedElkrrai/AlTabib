package com.example.altabib.featuers.favorites.presentation

import com.example.altabib.core.domain.util.DataError

sealed class FavoritesEvent {
    data class ShowToast(val error: DataError) : FavoritesEvent()
    data class Navigate(val route: String) : FavoritesEvent()
}