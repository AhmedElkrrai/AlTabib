package com.example.user.domain.usecases

import com.example.user.domain.AuthRepository
import com.example.user.domain.entities.User

class CacheUserUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(user: User) {
        repository.cacheUser(user)
    }
}
