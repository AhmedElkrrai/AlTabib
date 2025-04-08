package com.example.altabib.featuers.user.domain.usecases

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.user.domain.AuthRepository
import com.google.firebase.auth.FirebaseUser

class GoogleSignInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(idToken: String): Result<FirebaseUser, DataError> {
        return repository.signInWithGoogle(idToken)
    }
}
