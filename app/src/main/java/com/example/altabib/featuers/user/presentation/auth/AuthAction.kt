package com.example.altabib.featuers.user.presentation.auth

import com.example.altabib.featuers.user.domain.entities.User

sealed interface AuthAction {
    data class OnGoogleSignIn(val user: User, val idToken: String) : AuthAction
}
