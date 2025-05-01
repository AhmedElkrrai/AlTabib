package com.example.appointments.presentation

import com.example.appointments.presentation.models.AppointmentUi

sealed interface AppointmentsAction {
    data class Dismiss(val appointment: AppointmentUi) : AppointmentsAction
}