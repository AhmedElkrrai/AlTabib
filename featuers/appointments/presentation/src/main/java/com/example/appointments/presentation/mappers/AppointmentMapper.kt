package com.example.appointments.presentation.mappers

import com.example.appointments.domain.entities.Appointment
import com.example.appointments.presentation.models.AppointmentUi
import com.example.appointments.presentation.models.PatientName

fun Appointment.toUiModel(patientName: String): AppointmentUi {
    return AppointmentUi(
        id = id,
        doctorId = doctorId,
        patientName = PatientName(patientName),
        date = date
    )
}
