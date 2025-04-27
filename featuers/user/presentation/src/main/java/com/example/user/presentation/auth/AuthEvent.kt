package com.example.user.presentation.auth

import com.example.altabib.core.DataError

sealed interface AuthEvent {
    data class Navigate(val route: String) : AuthEvent
    data class ShowToast(val error: DataError) : AuthEvent
}
