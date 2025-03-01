package com.example.altabib.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.altabib.navigation.BottomNavDestination

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavDestination.Favorites.route
    ) {
        composable(BottomNavDestination.Favorites.route) {

        }
        composable(BottomNavDestination.Home.route) {

        }
        composable(BottomNavDestination.Settings.route) {

        }
    }
}
