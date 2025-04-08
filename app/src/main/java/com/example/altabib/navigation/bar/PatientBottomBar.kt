package com.example.altabib.navigation.bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.altabib.navigation.screen.PatientScreen
import com.example.altabib.navigation.utils.currentRoute

@Composable
fun PatientBottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(
            label = "Favorites",
            icon = Icons.Default.Favorite,
            route = PatientScreen.Favorites.route
        ),
        BottomNavItem(
            label = "Dashboard",
            icon = Icons.Default.Home,
            route = PatientScreen.Dashboard.route
        ),
        BottomNavItem(
            label = "Settings",
            icon = Icons.Default.Settings,
            route = PatientScreen.Settings.route
        )
    )

    BottomNavBar(
        items = items,
        navController = navController
    )
}
