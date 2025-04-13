package com.example.altabib.featuers.favorites.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.featuers.dashboard.domain.entities.getDisplayName
import com.example.altabib.featuers.favorites.presentation.FavoritesAction
import com.example.altabib.featuers.favorites.presentation.FavoritesState
import com.example.altabib.ui.components.Loading

@Composable
fun FavoritesScreen(
    state: FavoritesState,
    onAction: (FavoritesAction) -> Unit
) {
    if (state.isLoading) {
        Loading()
    } else if (state.favorites.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No favorite doctors yet.")
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(state.favorites) { _, doctor ->
                FavoriteDoctorCard(doctor = doctor)
            }
        }
    }
}

@Composable
fun FavoriteDoctorCard(doctor: Doctor) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /* Navigate to DoctorDetails */ }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Column {
                Text(doctor.name, style = MaterialTheme.typography.titleMedium)
                Text(doctor.specialization.getDisplayName(LocalContext.current))
                Text("⭐ ${doctor.rating}")
                Text(doctor.city)
            }
        }
    }
}
