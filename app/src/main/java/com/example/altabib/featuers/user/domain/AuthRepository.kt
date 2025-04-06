package com.example.altabib.featuers.user.domain

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result

interface AuthRepository{
    suspend fun registerUser(user: User): Result<User, DataError>
}
