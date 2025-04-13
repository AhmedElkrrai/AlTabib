package com.example.altabib.featuers.settings.data.source

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.settings.domain.PatientRepository
import com.example.altabib.featuers.settings.domain.entities.Patient
import com.example.altabib.featuers.user.data.source.remote.PATIENTS_PATH
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class PatientRepositoryImpl(
    private val firestore: FirebaseFirestore
) : PatientRepository {

    override suspend fun getPatient(uid: String?): Result<Patient, DataError>? {
        if (uid == null) return Result.Error(DataError.RetrievalError("Invalid user ID"))

        return try {
            val snapshot = firestore
                .collection(PATIENTS_PATH)
                .document(uid)
                .get()
                .await()

            if (snapshot.exists()) {
                val patient = snapshot.toObject(Patient::class.java)
                if (patient != null) {
                    Result.Success(patient)
                } else {
                    Result.Error(DataError.RetrievalError("Failed to parse patient"))
                }
            } else {
                Result.Error(DataError.RetrievalError("Patient not found"))
            }
        } catch (e: Exception) {
            Result.Error(DataError.RetrievalError(e.message ?: "Unknown error"))
        }
    }

    override suspend fun updatePatient(patient: Patient): Result<Patient, DataError> {
        return try {
            firestore
                .collection(PATIENTS_PATH)
                .document(patient.uid)
                .set(patient)
                .await()

            Result.Success(patient)
        } catch (e: Exception) {
            Result.Error(DataError.WriteError(e.message ?: "Failed to update patient"))
        }
    }
}
