package com.example.favorites.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.favorites.presentation.FavoritesAction
import com.example.favorites.presentation.FavoritesState
import com.example.altabib.design_system.components.Loading
import com.example.altabib.design_system.components.VerticalGrid

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
