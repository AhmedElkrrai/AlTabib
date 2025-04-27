package com.example.user.domain.mappers

import com.example.user.domain.entities.Doctor
import com.example.user.domain.entities.Patient
import com.example.user.domain.entities.User
import com.example.user.domain.entities.UserType

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
