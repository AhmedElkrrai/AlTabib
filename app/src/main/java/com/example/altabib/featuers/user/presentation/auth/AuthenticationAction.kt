package com.example.altabib.featuers.user.presentation.auth

import com.example.altabib.featuers.user.domain.entities.User

sealed interface AuthenticationAction {
    data class OnGoogleSignIn(val user: User, val idToken: String) : AuthenticationAction
}
