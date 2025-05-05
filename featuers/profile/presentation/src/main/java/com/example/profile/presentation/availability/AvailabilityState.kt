package com.example.profile.presentation.availability

import com.example.user.domain.entities.Availability

data class AvailabilityState(
    val isLoading: Boolean = false,
    val data: Availability = Availability()
)
