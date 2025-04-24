package com.example.altabib.featuers.user.data.source

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.altabib.core.map
import com.example.altabib.featuers.user.domain.entities.Doctor
import com.example.altabib.featuers.user.domain.entities.Patient
import com.example.altabib.featuers.user.data.source.local.UserManager
import com.example.altabib.featuers.user.data.source.remote.AuthenticationService
import com.example.altabib.featuers.user.data.source.remote.mappers.toUser
import com.example.altabib.featuers.user.domain.AuthRepository
import com.example.altabib.featuers.user.domain.entities.User

class AuthRepositoryImpl(
    private val authenticationService: AuthenticationService,
    private val userManager: UserManager
) : AuthRepository {

    override suspend fun registerPatient(patient: Patient): Result<User, DataError> {
        return authenticationService
            .registerPatient(patient)
            .map { it.toUser() }
    }

    override suspend fun registerDoctor(doctor: Doctor): Result<User, DataError> {
        return authenticationService
            .registerDoctor(doctor)
            .map { it.toUser() }
    }

    override suspend fun signInWithGoogle(idToken: String): Result<User, DataError> {
        return authenticationService.signInWithGoogle(idToken)
    }

    override fun logout() {
        authenticationService.logout()
        userManager.clearUser()
    }

    override fun cacheUser(user: User) {
        userManager.cacheUser(user)
    }

    override fun getUser(): User? {
        return userManager.getUser()
    }
}
