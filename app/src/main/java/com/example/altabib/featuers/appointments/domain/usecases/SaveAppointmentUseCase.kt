package com.example.altabib.featuers.appointments.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.altabib.featuers.appointments.domain.AppointmentRepository
import com.example.altabib.featuers.dashboard.domain.entities.Appointment

class SaveAppointmentUseCase(
    private val repository: AppointmentRepository
) {
    suspend operator fun invoke(appointment: Appointment): Result<Unit, DataError> {
        return repository.saveAppointment(appointment)
    }
}
