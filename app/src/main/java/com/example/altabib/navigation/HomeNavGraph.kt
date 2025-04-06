package com.example.altabib.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavDestination.Dashboard.route
    ) {
        composable(BottomNavDestination.Dashboard.route) {

        }

        composable(BottomNavDestination.Favorites.route) {

        }

        composable(BottomNavDestination.Settings.route) {

        }
    }
}
