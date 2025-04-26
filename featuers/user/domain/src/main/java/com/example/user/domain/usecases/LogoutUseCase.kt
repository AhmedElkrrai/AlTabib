package com.example.user.domain.usecases

import com.example.user.domain.AuthRepository

class LogoutUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke() {
        repository.logout()
    }
}
