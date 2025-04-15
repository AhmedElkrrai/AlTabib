package com.example.altabib.featuers.appointments.domain

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.dashboard.domain.entities.Appointment

interface AppointmentRepository {
    suspend fun saveAppointment(appointment: Appointment): Result<Unit, DataError>
}
