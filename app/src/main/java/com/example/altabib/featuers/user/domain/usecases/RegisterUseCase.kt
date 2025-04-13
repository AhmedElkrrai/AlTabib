package com.example.altabib.featuers.user.domain.usecases

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.featuers.settings.domain.entities.Patient
import com.example.altabib.featuers.user.domain.AuthRepository
import com.example.altabib.featuers.user.domain.entities.User
import com.example.altabib.featuers.user.domain.entities.UserType

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(user: User): Result<User, DataError> {
        return if (user.type == UserType.Patient) {
            val patient = Patient(
                uid = user.uid,
                name = user.name,
                city = user.city
            )
            repository.registerPatient(patient)
        } else {
            val doctor = Doctor(
                id = user.uid,
                name = user.name,
                city = user.city
            )
            repository.registerDoctor(doctor)
        }
    }
}
