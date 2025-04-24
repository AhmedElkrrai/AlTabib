package com.example.altabib.featuers.dashboard.presentation.dashboard

import com.example.altabib.featuers.user.domain.entities.Doctor
import com.example.altabib.featuers.dashboard.presentation.specialization.models.Specialization

sealed class DashboardAction {
    data class OnSearchQueryChanged(val query: String) : DashboardAction()
    data class OpenSpecializationScreen(val specialization: Specialization) : DashboardAction()
    data class OpenDoctorDetails(val doctor: Doctor) : DashboardAction()
}
