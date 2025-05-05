package com.example.profile.presentation.availability

import com.example.altabib.core.DataError

sealed interface AvailabilityEvent {
    data object Back : AvailabilityEvent
    data class ShowToast(val error: DataError) : AvailabilityEvent
    data class ShowMessage(val msgRes: Int) : AvailabilityEvent
}