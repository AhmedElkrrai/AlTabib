package com.example.altabib.design_system.navigation.screen

import com.example.altabib.core.DOCTOR_ID
import com.example.altabib.core.SPECIALIZATION

sealed class PatientScreen(val route: String) {
    data object Dashboard : PatientScreen("dashboard")
    data object Favorites : PatientScreen("favorites")
    data object Settings : PatientScreen("patient_settings")
    data object Specialization : PatientScreen("doctors_by_specialization/{$SPECIALIZATION}") {
        fun createRoute(specialization: String): String {
            return "doctors_by_specialization/$specialization"
        }
    }

    data object DoctorDetails : PatientScreen("doctor_details/{$DOCTOR_ID}") {
        fun createRoute(doctorId: String): String {
            return "doctor_details/$doctorId"
        }
    }

    data object Booking : PatientScreen("booking/{$DOCTOR_ID}") {
        fun createRoute(doctorId: String): String {
            return "booking/$doctorId"
        }
    }
}
