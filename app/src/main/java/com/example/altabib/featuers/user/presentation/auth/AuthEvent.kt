package com.example.altabib.featuers.user.presentation.auth

import com.example.altabib.core.domain.util.DataError

sealed interface AuthEvent {
    data class Navigate(val route: String) : AuthEvent
    data class ShowToast(val error: DataError) : AuthEvent
}
