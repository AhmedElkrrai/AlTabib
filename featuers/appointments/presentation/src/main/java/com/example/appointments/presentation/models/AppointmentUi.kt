package com.example.appointments.presentation.models

data class AppointmentUi(
    val id: String = "",
    val doctorId: String = "",
    val patientName: PatientName = PatientName(""),
    val date: String = "",
)
