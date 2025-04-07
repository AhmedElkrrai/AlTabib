package com.example.altabib.featuers.user.presentation.auth

import com.example.altabib.core.domain.util.DataError

data class AuthState(
    val isLoading: Boolean = false,
    val error: DataError? = null
)
