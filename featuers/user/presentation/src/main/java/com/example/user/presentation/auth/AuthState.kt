package com.example.user.presentation.auth

import com.example.altabib.core.DataError

data class AuthState(
    val isLoading: Boolean = false,
    val error: DataError? = null
)
