package com.example.altabib.featuers.user.data.source

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.user.data.source.local.UserManager
import com.example.altabib.featuers.user.data.source.remote.AuthenticationService
import com.example.altabib.featuers.user.domain.AuthRepository
import com.example.altabib.featuers.user.domain.entities.User
import com.google.firebase.auth.FirebaseUser

class AuthRepositoryImpl(
    private val authenticationService: AuthenticationService,
    private val userManager: UserManager
) : AuthRepository {
    override suspend fun registerUser(user: User): Result<User, DataError> {
        return authenticationService.registerUser(user)
    }

    override suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser, DataError> {
        return authenticationService.signInWithGoogle(idToken)
    }

    override fun logout() {
        authenticationService.logout()
        userManager.clearUser()
    }

    override fun saveUser(user: User) {
        userManager.saveUser(user)
    }

    override fun getUser(): User? {
        return userManager.getUser()
    }
}
