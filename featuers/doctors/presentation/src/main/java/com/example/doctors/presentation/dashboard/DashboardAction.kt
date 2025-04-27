package com.example.doctors.presentation.dashboard

import com.example.altabib.design_system.models.Specialization
import com.example.user.domain.entities.Doctor

sealed class DashboardAction {
    data class OnSearchQueryChanged(val query: String) : DashboardAction()
    data class OpenSpecializationScreen(val specialization: Specialization) : DashboardAction()
    data class OpenDoctorDetails(val doctor: Doctor) : DashboardAction()
}
