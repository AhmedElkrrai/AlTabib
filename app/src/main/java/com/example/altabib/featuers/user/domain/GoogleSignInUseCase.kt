package com.example.altabib.featuers.user.domain

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result

class GoogleSignInUseCase(private val authRepository: AuthRepository) {
    suspend fun register(user:User): Result<User, DataError> {
        return authRepository.registerUser(user)
    }
}
