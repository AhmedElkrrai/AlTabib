package com.example.altabib.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.analytics.presentation.AnalyticsScreen
import com.example.appointments.presentation.components.AppointmentsScreenRoot
import com.example.altabib.design_system.navigation.screen.DoctorScreen
import com.example.profile.presentation.components.ProfileScreenRoot

@Composable
fun DoctorNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination = DoctorScreen.Appointments.route) {
        composable(DoctorScreen.Analytics.route) {
            AnalyticsScreen(navController)
        }
        composable(DoctorScreen.Appointments.route) {
            AppointmentsScreenRoot(modifier)
        }
        composable(DoctorScreen.Profile.route) {
            ProfileScreenRoot(modifier)
        }
    }
}
