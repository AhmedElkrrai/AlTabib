package com.example.altabib.navigation.screen

import com.example.altabib.navigation.utils.DOCTOR_ID
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
    data object ContactUs : PatientScreen("contact_us")
    data object EditProfile : PatientScreen("edit_profile")
}
