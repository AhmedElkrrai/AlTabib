package com.example.user.domain.entities

data class User(
    val uid: String = "",
    val name: String = "",
    val city: String = "",
    val type: UserType = UserType.Patient,
)
