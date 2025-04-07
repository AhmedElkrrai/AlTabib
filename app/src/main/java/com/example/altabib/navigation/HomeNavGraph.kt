package com.example.altabib.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavDestination.Dashboard.route
    ) {
        composable(BottomNavDestination.Dashboard.route) {
            DashboardScreen()
        }

        composable(BottomNavDestination.Favorites.route) {
            FavoritesScreen()
        }

        composable(BottomNavDestination.Settings.route) {
            SettingsScreen()
        }
    }
}

@Composable
fun DashboardScreen() {
    Text("Welcome to the Dashboard", modifier = Modifier.fillMaxSize())
}

@Composable
fun FavoritesScreen() {
    Text("Your Favorite Doctors", modifier = Modifier.fillMaxSize())
}

@Composable
fun SettingsScreen() {
    Text("Settings", modifier = Modifier.fillMaxSize())
}
