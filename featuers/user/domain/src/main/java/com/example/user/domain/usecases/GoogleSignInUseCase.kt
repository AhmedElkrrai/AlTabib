package com.example.user.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.user.domain.AuthRepository
import com.example.user.domain.entities.User

class GoogleSignInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(idToken: String): Result<User, DataError> {
        return repository.signInWithGoogle(idToken)
    }
}
