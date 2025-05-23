package com.example.altabib.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.altabib.core.DOCTOR_ID
import com.example.altabib.core.SPECIALIZATION
import com.example.altabib.design_system.navigation.screen.PatientScreen
import com.example.favorites.presentation.components.FavoritesScreenRoot
import com.example.altabib.utils.ChangeLanguage
import com.example.doctors.presentation.booking.components.BookingScreenRoot
import com.example.doctors.presentation.dashboard.components.DashboardScreenRoot
import com.example.doctors.presentation.doctor.components.DoctorDetailsScreenRoot
import com.example.doctors.presentation.specialization.components.SpecializationScreenRoot
import com.example.settings.presentation.components.SettingsScreenRoot

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
            SettingsScreenRoot { language -> ChangeLanguage(language) }
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
