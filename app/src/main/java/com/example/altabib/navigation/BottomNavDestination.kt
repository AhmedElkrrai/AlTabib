package com.example.altabib.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavDestination(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Favorites : BottomNavDestination(
        route = "favorites",
        label = "Favorites",
        icon = Icons.Filled.Restaurant
    )

    object Home : BottomNavDestination(
        route = "home",
        label = "Home",
        icon = Icons.Filled.MenuBook
    )

    object Settings : BottomNavDestination(
        route = "settings",
        label = "Settings",
        icon = Icons.Filled.Settings
    )
}
