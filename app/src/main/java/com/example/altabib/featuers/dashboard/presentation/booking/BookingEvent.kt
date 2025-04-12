package com.example.altabib.featuers.dashboard.presentation.booking

import com.example.altabib.core.domain.util.DataError

sealed class BookingEvent {
    data object Back : BookingEvent()
    data class ShowToast(val error: DataError) : BookingEvent()
}
