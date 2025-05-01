package com.example.appointments.domain.entities

data class Appointment(
    val id: String = "",
    val doctorId: String = "",
    val patientId: String = "",
    val date: String = "",
)
