package com.example.doctors.presentation.specialization

import com.example.user.domain.entities.Doctor

data class SpecializationState(
    val isLoading: Boolean = false,
    val doctors: List<Doctor> = emptyList()
)
