package com.example.altabib.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.altabib.featuers.dashboard.presentation.booking.components.BookingScreenRoot
import com.example.altabib.featuers.dashboard.presentation.dashboard.components.DashboardScreenRoot
import com.example.altabib.featuers.dashboard.presentation.doctor.components.DoctorDetailsScreenRoot
import com.example.altabib.featuers.dashboard.presentation.specialization.components.SpecializationScreenRoot
import com.example.altabib.featuers.favorites.presentation.components.FavoritesScreenRoot
import com.example.altabib.featuers.settings.presentation.components.SettingsScreenRoot
import com.example.altabib.navigation.screen.PatientScreen
import com.example.altabib.navigation.utils.DOCTOR_ID
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
            FavoritesScreenRoot(navController)
        }
        composable(PatientScreen.Settings.route) {
            SettingsScreenRoot()
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
        composable(PatientScreen.DoctorDetails.route) { backStackEntry ->
            val doctorId = backStackEntry.arguments?.getString(DOCTOR_ID)
            doctorId?.let {
                DoctorDetailsScreenRoot(
                    doctorId = it,
                    navController = navController
                )
            }
        }
        composable(PatientScreen.Booking.route) { backStackEntry ->
            val doctorId = backStackEntry.arguments?.getString(DOCTOR_ID)
            doctorId?.let {
                BookingScreenRoot(
                    doctorId = it,
                    navController = navController
                )
            }
        }
    }
}
