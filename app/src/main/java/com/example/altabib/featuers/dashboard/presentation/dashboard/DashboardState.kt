package com.example.altabib.featuers.dashboard.presentation.dashboard

import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.featuers.dashboard.domain.entities.Specialization

data class DashboardState(
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val searchResult: List<Doctor> = emptyList(),
    val specializations: List<Specialization> = emptyList()
)
