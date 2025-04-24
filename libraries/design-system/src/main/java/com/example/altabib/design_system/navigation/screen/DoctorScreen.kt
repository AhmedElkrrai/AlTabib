package com.example.altabib.design_system.navigation.screen

sealed class DoctorScreen(val route: String) {
    data object Appointments : DoctorScreen("doctor_appointments")
    data object Analytics : DoctorScreen("doctor_analytics")
    data object Profile : DoctorScreen("doctor_profile")
}
