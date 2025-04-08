package com.example.altabib.featuers.user.domain.usecases

import com.example.altabib.featuers.user.domain.AuthRepository
import com.example.altabib.featuers.user.domain.entities.User

class GetUserUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(): User? {
        return repository.getUser()
    }
}
