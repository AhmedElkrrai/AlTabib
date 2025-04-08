package com.example.altabib.navigation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.altabib.featuers.analytics.presentation.AnalyticsScreen
import com.example.altabib.featuers.appointments.presentation.AppointmentsScreen
import com.example.altabib.featuers.settings.presentation.SettingsScreenRoot
import com.example.altabib.navigation.screen.DoctorScreen

@Composable
fun DoctorNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(navController, startDestination = DoctorScreen.Appointments.route) {
        composable(DoctorScreen.Analytics.route) {
            AnalyticsScreen(navController)
        }
        composable(DoctorScreen.Appointments.route) {
            AppointmentsScreen(navController)
        }
        composable(DoctorScreen.Settings.route) {
            SettingsScreenRoot(navController)
        }
    }
}
