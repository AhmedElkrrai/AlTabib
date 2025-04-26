package com.example.altabib.featuers.settings.data.source

import android.util.Log
import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.altabib.featuers.settings.domain.PatientRepository
import com.example.user.domain.entities.Patient
import com.example.altabib.featuers.user.data.source.remote.PATIENTS_PATH
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class PatientRepositoryImpl(
    private val firestore: FirebaseFirestore
) : PatientRepository {

    override suspend fun getPatient(uid: String?): Result<Patient, DataError>? {
        if (uid == null) return Result.Error(DataError.FailedToRetrieveData)

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
                    Result.Error(DataError.FailedToRetrieveData)
                }
            } else {
                Result.Error(DataError.FailedToRetrieveData)
            }
        } catch (e: Exception) {
            Log.e("PatientRepo", "Error in getPatient", e)
            Result.Error(DataError.FailedToRetrieveData)
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
            Log.e("PatientRepo", "Error in updatePatient", e)
            Result.Error(DataError.FailedToUpdateData)
        }
    }
}
