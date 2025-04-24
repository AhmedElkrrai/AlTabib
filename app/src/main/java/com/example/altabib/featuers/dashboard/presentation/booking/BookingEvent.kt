package com.example.altabib.featuers.dashboard.presentation.booking

import com.example.altabib.core.DataError

sealed class BookingEvent {
    data object Back : BookingEvent()
    data class ShowToast(val error: DataError) : BookingEvent()
    data class ShowMessage(val msgRes: Int) : BookingEvent()
}
