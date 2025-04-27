package com.example.user.presentation.auth

import com.example.user.domain.entities.User

sealed interface AuthAction {
    data class OnGoogleSignIn(val user: User, val idToken: String) : AuthAction
}
