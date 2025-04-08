package com.example.altabib.navigation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.altabib.featuers.dashboard.presentation.DashboardScreen
import com.example.altabib.featuers.favorites.presentation.FavoritesScreen
import com.example.altabib.featuers.settings.presentation.SettingsScreenRoot
import com.example.altabib.featuers.specialization.presentation.SpecializationScreenRoot
import com.example.altabib.navigation.utils.SPECIALIZATION
import com.example.altabib.navigation.screen.PatientScreen

@Composable
fun PatientNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(navController, startDestination = PatientScreen.Dashboard.route) {
        composable(PatientScreen.Dashboard.route) {
            DashboardScreen(navController)
        }
        composable(PatientScreen.Favorites.route) {
            FavoritesScreen()
        }
        composable(PatientScreen.Settings.route) {
            SettingsScreenRoot(navController)
        }
        composable(PatientScreen.Specialization.route) { backStackEntry ->
            val specialization = backStackEntry.arguments?.getString(SPECIALIZATION)
            specialization?.let {
                SpecializationScreenRoot(specialization)
            }
        }
    }
}
