package com.example.user.data.source.remote.mappers

import com.example.user.domain.entities.User
import com.example.user.domain.entities.UserType.Patient
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toDomain(): User {
    return User(
        uid = uid,
        name = displayName ?: "Unknown",
        city = "Unknown",
        type = Patient
    )
}
