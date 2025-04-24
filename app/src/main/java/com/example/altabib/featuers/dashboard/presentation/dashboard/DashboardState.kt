package com.example.altabib.featuers.dashboard.presentation.dashboard

import com.example.altabib.featuers.user.domain.entities.Doctor
import com.example.altabib.featuers.dashboard.presentation.specialization.models.Specialization

data class DashboardState(
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val searchResult: List<Doctor> = emptyList(),
    val specializations: List<Specialization> = emptyList()
)
