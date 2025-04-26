package com.example.altabib.featuers.dashboard.presentation.dashboard

import com.example.altabib.featuers.dashboard.presentation.specialization.models.Specialization
import com.example.user.domain.entities.Doctor

data class DashboardState(
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val searchResult: List<Doctor> = emptyList(),
    val specializations: List<Specialization> = emptyList()
)
