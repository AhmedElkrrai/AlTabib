package com.example.altabib.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.altabib.featuers.dashboard.presentation.dashboard.components.DashboardScreenRoot
import com.example.altabib.featuers.favorites.presentation.FavoritesScreen
import com.example.altabib.featuers.settings.presentation.SettingsScreenRoot
import com.example.altabib.featuers.dashboard.presentation.specialization.components.SpecializationScreenRoot
import com.example.altabib.navigation.screen.PatientScreen
import com.example.altabib.navigation.utils.SPECIALIZATION

@Composable
fun PatientNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = PatientScreen.Dashboard.route) {
        composable(PatientScreen.Dashboard.route) {
            DashboardScreenRoot(navController, modifier)
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
                SpecializationScreenRoot(
                    key = specialization,
                    navController = navController
                )
            }
        }
        composable(PatientScreen.DoctorDetails.route) {
           // DoctorDetailsScreen()
        }
    }
}
