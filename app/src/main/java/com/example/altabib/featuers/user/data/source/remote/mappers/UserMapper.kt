package com.example.altabib.featuers.user.data.source.remote.mappers

import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.featuers.settings.domain.entities.Patient
import com.example.altabib.featuers.user.domain.entities.User
import com.example.altabib.featuers.user.domain.entities.UserType
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toDomain(): User {
    return User(
        uid = uid,
        name = displayName ?: "Unknown",
        city = "Unknown",
        type = UserType.Patient
    )
}

fun Patient.toUser(): User {
    return User(
        uid = uid,
        name = name,
        city = city,
        type = UserType.Patient
    )
}

fun Doctor.toUser(): User {
    return User(
        uid = id,
        name = name,
        city = city,
        type = UserType.Doctor
    )
}
