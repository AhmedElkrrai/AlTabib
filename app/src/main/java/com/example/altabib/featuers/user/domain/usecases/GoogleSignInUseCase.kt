package com.example.altabib.featuers.user.domain.usecases

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.user.domain.AuthRepository
import com.example.altabib.featuers.user.domain.entities.User

class GoogleSignInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(idToken: String): Result<User, DataError> {
        return repository.signInWithGoogle(idToken)
    }
}
