package com.example.altabib.featuers.user.domain

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.featuers.settings.domain.entities.Patient
import com.example.altabib.featuers.user.domain.entities.User

interface AuthRepository {
    suspend fun registerPatient(patient: Patient): Result<User, DataError>
    suspend fun registerDoctor(doctor: Doctor): Result<User, DataError>
    suspend fun signInWithGoogle(idToken: String): Result<User, DataError>
    fun logout()
    fun cacheUser(user: User)
    fun getUser(): User?
}
