package com.example.appointments.presentation

import com.example.appointments.presentation.models.AppointmentUi

data class AppointmentsState(
    val appointments: List<AppointmentUi> = emptyList(),
    val isLoading: Boolean = false,
)