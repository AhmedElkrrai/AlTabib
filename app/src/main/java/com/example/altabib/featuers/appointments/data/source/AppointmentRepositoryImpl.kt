package com.example.altabib.featuers.appointments.data.source

import android.util.Log
import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.appointments.domain.AppointmentRepository
import com.example.altabib.featuers.dashboard.domain.entities.Appointment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

private const val APPOINTMENTS_PATH = "appointments"

class AppointmentRepositoryImpl(
    private val firestore: FirebaseFirestore
) : AppointmentRepository {
    override suspend fun saveAppointment(
        appointment: Appointment
    ): Result<Unit, DataError> {
        return try {
            firestore.collection(APPOINTMENTS_PATH)
                .document(appointment.id)
                .set(appointment)
                .await()
            Result.Success(Unit)
        } catch (e: Exception) {
            Log.e("AppointmentRepo", "Error in saveAppointment", e)
            Result.Error(DataError.GeneralError)
        }
    }
}