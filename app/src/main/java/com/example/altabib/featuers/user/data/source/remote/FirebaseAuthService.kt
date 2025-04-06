package com.example.altabib.featuers.user.data.source.remote

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.user.domain.User

class FirebaseAuthService {
    suspend fun registerUser(user: User): Result<User, DataError> {
        // Call Firebase Authentication or your custom logic here
        // For simplicity, returning a success result
        return Result.Success(user)
    }
}
