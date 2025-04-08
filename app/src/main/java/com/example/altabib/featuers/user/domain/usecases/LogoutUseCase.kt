package com.example.altabib.featuers.user.domain.usecases

import com.example.altabib.featuers.user.domain.AuthRepository

class LogoutUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke() {
        repository.logout()
    }
}
