package com.example.altabib.featuers.settings.domain.usecases

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.settings.domain.PatientRepository
import com.example.altabib.featuers.settings.domain.entities.Patient

class UpdatePatientUseCase(
    private val repository: PatientRepository
) {
    suspend operator fun invoke(patient: Patient): Result<Patient, DataError> {
        return repository.updatePatient(patient)
    }
}
