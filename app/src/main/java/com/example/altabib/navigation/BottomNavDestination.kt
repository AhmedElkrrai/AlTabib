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
    data object Favorites : BottomNavDestination(
        route = "favorites",
        label = "Favorites",
        icon = Icons.Filled.Restaurant
    )

    data object Dashboard : BottomNavDestination(
        route = "dashboard",
        label = "Dashboard",
        icon = Icons.Filled.MenuBook
    )

    data object Settings : BottomNavDestination(
        route = "settings",
        label = "Settings",
        icon = Icons.Filled.Settings
    )
}
