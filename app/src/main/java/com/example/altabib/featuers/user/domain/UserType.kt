package com.example.altabib.featuers.user.domain

enum class UserType(val key: String) {
    Doctor("Doctor"),
    Patient("Patient");

    companion object {
        fun fromKey(key: String): UserType = entries.find { it.key == key } ?: Patient
    }
}