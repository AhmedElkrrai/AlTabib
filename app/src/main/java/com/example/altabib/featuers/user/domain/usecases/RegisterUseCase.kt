package com.example.altabib.featuers.user.domain.usecases

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.user.domain.AuthRepository
import com.example.altabib.featuers.user.domain.entities.User

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(user: User): Result<User, DataError> {
        return repository.registerUser(user)
    }
}
