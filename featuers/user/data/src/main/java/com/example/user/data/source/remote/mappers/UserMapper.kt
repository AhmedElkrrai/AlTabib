package com.example.user.data.source.remote.mappers

import com.example.user.domain.entities.Doctor
import com.example.user.domain.entities.Patient
import com.example.user.domain.entities.User
import com.example.user.domain.entities.UserType.*
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toDomain(): User {
    return User(
        uid = uid,
        name = displayName ?: "Unknown",
        city = "Unknown",
        type = Patient
    )
}

fun Patient.toUser(): User {
    return User(
        uid = uid,
        name = name,
        city = city,
        type = Patient
    )
}

fun Doctor.toUser(): User {
    return User(
        uid = id,
        name = name,
        city = city,
        type = Doctor
    )
}
