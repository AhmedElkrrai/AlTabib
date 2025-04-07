package com.example.altabib.featuers.user.data.source.remote

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.user.domain.AuthRepository
import com.example.altabib.featuers.user.domain.User
import com.google.firebase.auth.FirebaseUser

class AuthRepositoryImpl(
    private val firebaseAuthService: FirebaseAuthService
) : AuthRepository {
    override suspend fun registerUser(user: User): Result<User, DataError> {
        return firebaseAuthService.registerUser(user)
    }

    override suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser, DataError> {
        return firebaseAuthService.signInWithGoogle(idToken)
    }
}
