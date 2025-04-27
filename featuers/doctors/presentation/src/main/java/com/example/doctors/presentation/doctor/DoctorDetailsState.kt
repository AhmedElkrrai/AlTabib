package com.example.doctors.presentation.doctor

import com.example.user.domain.entities.Doctor

data class DoctorDetailsState(
    val isLoading: Boolean = false,
    val doctor: Doctor? = null,
    val userRating: Int? = null,
    val error: String? = null
)
