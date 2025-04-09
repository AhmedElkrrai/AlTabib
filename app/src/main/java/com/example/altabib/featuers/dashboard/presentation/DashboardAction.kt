package com.example.altabib.featuers.dashboard.presentation

import com.example.altabib.featuers.specialization.domain.entities.Specialization

sealed class DashboardAction {
    data class OnSearchQueryChanged(val query: String) : DashboardAction()
    data class OpenSpecializationScreen(val specialization: Specialization) : DashboardAction()
}
