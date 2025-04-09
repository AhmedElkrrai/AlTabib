package com.example.altabib.featuers.dashboard.presentation

import com.example.altabib.featuers.specialization.domain.entities.Doctor
import com.example.altabib.featuers.specialization.domain.entities.Specialization

data class DashboardState(
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val searchResult: List<Doctor> = emptyList(),
    val specializations: List<Specialization> = emptyList()
)
