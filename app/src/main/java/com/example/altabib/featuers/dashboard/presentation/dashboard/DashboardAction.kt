package com.example.altabib.featuers.dashboard.presentation.dashboard

import com.example.altabib.featuers.dashboard.domain.entities.Specialization

sealed class DashboardAction {
    data class OnSearchQueryChanged(val query: String) : DashboardAction()
    data class OpenSpecializationScreen(val specialization: Specialization) : DashboardAction()
}
