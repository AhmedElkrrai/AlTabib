package com.example.altabib.featuers.user.domain.entities

enum class UserType(val key: String) {
    Doctor("Doctor"),
    Patient("Patient");

    companion object {
        fun fromKey(key: String): UserType = entries.find { it.key == key } ?: Patient
    }
}
