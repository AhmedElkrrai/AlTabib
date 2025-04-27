package com.example.settings.domain

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.user.domain.entities.Patient

interface PatientRepository {
    suspend fun updatePatient(patient: Patient): Result<Patient, DataError>
    suspend fun getPatient(uid: String?): Result<Patient, DataError>?
}
