package com.example.appointments.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.appointments.domain.AppointmentRepository
import com.example.appointments.domain.entities.Appointment

class SaveAppointmentUseCase(
    private val repository: AppointmentRepository
) {
    suspend operator fun invoke(appointment: Appointment): Result<Unit, DataError> {
        return repository.saveAppointment(appointment)
    }
}
