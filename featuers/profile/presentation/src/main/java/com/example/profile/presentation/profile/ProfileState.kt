package com.example.profile.presentation.profile

import com.example.user.domain.entities.Doctor

data class ProfileState(
    val isLoading: Boolean = false,
    val doctor: Doctor = Doctor(),
)
