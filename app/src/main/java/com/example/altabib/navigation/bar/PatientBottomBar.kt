package com.example.altabib.navigation.bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.altabib.R
import com.example.altabib.navigation.screen.PatientScreen
import com.example.altabib.utils.getLocalizedString

@Composable
fun PatientBottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(
            label = getLocalizedString(R.string.favorites),
            icon = Icons.Default.Favorite,
            route = PatientScreen.Favorites.route
        ),
        BottomNavItem(
            label = getLocalizedString(R.string.dashboard),
            icon = Icons.Default.Home,
            route = PatientScreen.Dashboard.route
        ),
        BottomNavItem(
            label = getLocalizedString(R.string.settings),
            icon = Icons.Default.Settings,
            route = PatientScreen.Settings.route
        )
    )

    BottomNavBar(
        items = items,
        navController = navController
    )
}
