package com.example.altabib.featuers.user.domain

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(user: User): Result<User, DataError> {
        return repository.registerUser(user)
    }
}
