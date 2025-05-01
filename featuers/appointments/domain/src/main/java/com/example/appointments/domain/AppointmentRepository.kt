package com.example.appointments.domain

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.appointments.domain.entities.Appointment

interface AppointmentRepository {
    suspend fun saveAppointment(appointment: Appointment): Result<Unit, DataError>
    suspend fun getAppointments(doctorId: String): Result<List<Appointment>, DataError>
}
