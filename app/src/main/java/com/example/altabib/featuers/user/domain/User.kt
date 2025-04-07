package com.example.altabib.featuers.user.domain

data class User(
    val uid: String = "",
    val name: String,
    val city: String,
    val type: UserType
)
