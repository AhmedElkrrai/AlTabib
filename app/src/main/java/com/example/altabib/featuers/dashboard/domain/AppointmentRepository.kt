package com.example.altabib.featuers.dashboard.domain

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.featuers.dashboard.domain.entities.Appointment
import com.example.altabib.core.domain.util.Result

interface AppointmentRepository {
    suspend fun saveAppointment(appointment: Appointment): Result<Unit, DataError>
}
