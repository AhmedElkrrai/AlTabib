package com.example.altabib.featuers.settings.domain

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.settings.domain.entities.Patient

interface PatientRepository {
    suspend fun updatePatient(patient: Patient): Result<Patient, DataError>
    suspend fun getPatient(uid: String?): Result<Patient, DataError>?
}
