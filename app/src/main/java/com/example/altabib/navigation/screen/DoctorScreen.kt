package com.example.altabib.navigation.screen

sealed class DoctorScreen(val route: String) {
    data object Appointments : DoctorScreen("doctor_appointments")
    data object Analytics : DoctorScreen("doctor_analytics")
    data object Profile : DoctorScreen("doctor_profile")
}
