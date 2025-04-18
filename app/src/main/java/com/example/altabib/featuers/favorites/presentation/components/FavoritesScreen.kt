package com.example.altabib.featuers.favorites.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.altabib.featuers.favorites.presentation.FavoritesAction
import com.example.altabib.featuers.favorites.presentation.FavoritesState
import com.example.altabib.ui.components.Loading
import com.example.altabib.ui.components.VerticalGrid

@Composable
fun FavoritesScreen(
    state: FavoritesState,
    onAction: (FavoritesAction) -> Unit
) {
    if (state.isLoading) {
        Loading()
    } else if (state.doctors.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No favorite doctors yet.")
        }
    } else {
        VerticalGrid {
            itemsIndexed(state.doctors) { _, doctor ->
                FavoriteCard(
                    doctor = doctor,
                    onClick = { onAction(FavoritesAction.OnDoctorClick(doctor.id)) },
                    onRemoveClick = { onAction(FavoritesAction.UnFavoriteDoctor(doctor)) }
                )
            }
        }
    }
}
