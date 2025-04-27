package com.example.settings.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.settings.domain.PatientRepository
import com.example.user.domain.entities.Patient

class UpdatePatientUseCase(
    private val repository: PatientRepository
) {
    suspend operator fun invoke(patient: Patient): Result<Patient, DataError> {
        return repository.updatePatient(patient)
    }
}
