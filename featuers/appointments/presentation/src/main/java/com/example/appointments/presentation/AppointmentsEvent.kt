package com.example.appointments.presentation

import com.example.altabib.core.DataError

sealed interface AppointmentsEvent {
    data class ShowToast(val error: DataError) : AppointmentsEvent
    data class ShowMessage(val msgRes: Int) : AppointmentsEvent
}