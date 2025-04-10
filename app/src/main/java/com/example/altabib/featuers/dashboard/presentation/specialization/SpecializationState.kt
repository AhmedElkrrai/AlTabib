package com.example.altabib.featuers.dashboard.presentation.specialization

import com.example.altabib.featuers.dashboard.domain.entities.Doctor

data class SpecializationState(
    val isLoading: Boolean = false,
    val doctors: List<Doctor> = emptyList()
)
