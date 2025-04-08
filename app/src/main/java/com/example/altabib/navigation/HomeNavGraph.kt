package com.example.altabib.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.altabib.featuers.dashboard.presentation.DashboardScreen
import com.example.altabib.featuers.favorites.presentation.FavoritesScreen
import com.example.altabib.featuers.settings.presentation.SettingsScreen
import com.example.altabib.featuers.settings.presentation.SettingsScreenRoot

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    padding: PaddingValues,
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
            SettingsScreenRoot()
        }
    }
}
