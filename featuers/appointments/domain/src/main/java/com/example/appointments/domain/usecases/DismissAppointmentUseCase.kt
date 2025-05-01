package com.example.appointments.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.appointments.domain.AppointmentRepository

class DismissAppointmentUseCase(
    private val repository: AppointmentRepository
) {
    suspend operator fun invoke(appointmentId: String): Result<Unit, DataError> {
        return repository.dismissAppointment(appointmentId)
    }
}
