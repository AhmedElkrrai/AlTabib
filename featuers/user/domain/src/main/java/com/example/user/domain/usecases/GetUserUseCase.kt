package com.example.user.domain.usecases

import com.example.user.domain.AuthRepository
import com.example.user.domain.entities.User

class GetUserUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(): User? {
        return repository.getUser()
    }
}
