package com.example.altabib.featuers.dashboard.presentation.specialization

import com.example.altabib.featuers.user.domain.entities.Doctor

data class SpecializationState(
    val isLoading: Boolean = false,
    val doctors: List<Doctor> = emptyList()
)
