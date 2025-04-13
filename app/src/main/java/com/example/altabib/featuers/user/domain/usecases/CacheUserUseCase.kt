package com.example.altabib.featuers.user.domain.usecases

import com.example.altabib.featuers.user.domain.AuthRepository
import com.example.altabib.featuers.user.domain.entities.User

class CacheUserUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(user: User) {
        repository.cacheUser(user)
    }
}
