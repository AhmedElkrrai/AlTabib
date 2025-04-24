package com.example.altabib.featuers.settings.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.altabib.featuers.settings.domain.PatientRepository
import com.example.altabib.featuers.settings.domain.entities.Patient
import com.example.altabib.featuers.user.domain.usecases.GetUserUseCase

class GetPatientUseCase(
    private val repository: PatientRepository,
    private val userUseCase: GetUserUseCase
) {
    suspend operator fun invoke(uid: String? = userUseCase()?.uid): Result<Patient, DataError>? {
        return repository.getPatient(uid)
    }
}
