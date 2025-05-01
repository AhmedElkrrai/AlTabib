package com.example.appointments.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.altabib.core.getTodayDate
import com.example.appointments.domain.AppointmentRepository
import com.example.appointments.domain.entities.Appointment
import com.example.user.domain.usecases.GetUserUseCase

class GetAppointmentsUseCase(
    private val repository: AppointmentRepository,
    private val getUserUseCase: GetUserUseCase
) {
    suspend operator fun invoke(
        date: String = getTodayDate()
    ): Result<List<Appointment>, DataError> {
        val doctor = getUserUseCase() ?: return Result.Error(DataError.LocalError)
        // TODO: pass doctor.uid
        return repository.getAppointments("doc_17", date)
    }
}
