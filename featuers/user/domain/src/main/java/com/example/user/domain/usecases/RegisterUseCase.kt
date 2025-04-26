package com.example.user.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.user.domain.entities.Doctor
import com.example.user.domain.entities.Patient
import com.example.user.domain.AuthRepository
import com.example.user.domain.entities.User
import com.example.user.domain.entities.UserType

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
