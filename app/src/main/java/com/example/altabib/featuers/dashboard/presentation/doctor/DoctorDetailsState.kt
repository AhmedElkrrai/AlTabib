package com.example.altabib.featuers.dashboard.presentation.doctor

import com.example.altabib.featuers.dashboard.domain.entities.Doctor

data class DoctorDetailsState(
    val isLoading: Boolean = false,
    val doctor: Doctor? = null,
)
