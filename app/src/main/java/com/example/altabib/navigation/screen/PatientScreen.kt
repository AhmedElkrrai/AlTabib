package com.example.altabib.navigation.screen

import com.example.altabib.navigation.utils.SPECIALIZATION

sealed class PatientScreen(val route: String) {
    data object Dashboard : PatientScreen("dashboard")
    data object Favorites : PatientScreen("favorites")
    data object Settings : PatientScreen("patient_settings")
    data object Specialization : PatientScreen("doctors_by_specialization/{$SPECIALIZATION}") {
        fun createRoute(specialization: String): String {
            return "doctors_by_specialization/$specialization"
        }
    }
}
