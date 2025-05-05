package com.example.profile.presentation.availability

import com.example.user.domain.entities.Availability

sealed interface AvailabilityAction {
    data class OnAvailabilityChange(val value: Availability) : AvailabilityAction
    data class Save(val value: Availability) : AvailabilityAction
    data object Back : AvailabilityAction
}