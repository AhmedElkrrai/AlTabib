package com.example.altabib.featuers.user.domain

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.user.domain.entities.User
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun registerUser(user: User): Result<User, DataError>
    suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser, DataError>
    fun logout()
}
