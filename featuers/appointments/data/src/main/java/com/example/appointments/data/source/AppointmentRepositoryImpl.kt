package com.example.appointments.data.source

import android.util.Log
import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.appointments.domain.AppointmentRepository
import com.example.appointments.domain.entities.Appointment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

private const val APPOINTMENTS_PATH = "appointments"
private const val DOCTOR_ID_FIELD = "doctorId"

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

    override suspend fun getAppointments(doctorId: String): Result<List<Appointment>, DataError> {
        return try {
            val appointments = firestore.collection(APPOINTMENTS_PATH)
                .whereEqualTo(DOCTOR_ID_FIELD, doctorId)
                .get()
                .await()
                .toObjects(Appointment::class.java)

            Result.Success(appointments)
        } catch (e: Exception) {
            Log.e("AppointmentRepo", "Error in getAppointments", e)
            Result.Error(DataError.GeneralError)
        }
    }

    override suspend fun dismissAppointment(appointmentId: String): Result<Unit, DataError> {
        return try {
            firestore.collection(APPOINTMENTS_PATH)
                .document(appointmentId)
                .delete()
                .await()

            Result.Success(Unit)
        } catch (e: Exception) {
            Log.e("AppointmentRepo", "Error in dismissAppointment", e)
            Result.Error(DataError.GeneralError)
        }
    }
}
