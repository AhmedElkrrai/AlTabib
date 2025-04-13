package com.example.altabib.featuers.favorites.presentation

sealed class FavoritesAction {
    data object LoadFavorites : FavoritesAction()
}