package com.example.altabib.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.altabib.design_system.navigation.screen.DoctorScreen
import com.example.altabib.utils.ChangeLanguage
import com.example.analytics.presentation.components.AnalyticsScreenRoot
import com.example.appointments.presentation.components.AppointmentsScreenRoot
import com.example.profile.presentation.availability.components.AvailabilityScreenRoot
import com.example.profile.presentation.profile.components.ProfileScreenRoot

@Composable
fun DoctorNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = DoctorScreen.Appointments.route) {
        composable(DoctorScreen.Analytics.route) {
            AnalyticsScreenRoot(
                navController = navController,
                modifier = modifier,
            )
        }
        composable(DoctorScreen.Appointments.route) {
            AppointmentsScreenRoot(modifier)
        }
        composable(DoctorScreen.Profile.route) {
            ProfileScreenRoot(
                navController = navController,
                modifier = modifier,
                onLanguageChanged = { ChangeLanguage() }
            )
        }
        composable(DoctorScreen.EditAvailability.route) {
            AvailabilityScreenRoot(navController)
        }
    }
}
