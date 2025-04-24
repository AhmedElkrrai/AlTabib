package com.example.altabib.featuers.user.domain.entities

import androidx.compose.runtime.Composable
import com.example.altabib.R
import com.example.altabib.design_system.getLocalizedString

enum class UserType(val key: String) {
    Doctor("Doctor"),
    Patient("Patient");

    companion object {
        fun fromKey(key: String): UserType = entries.find { it.key == key } ?: Patient
    }

    @Composable
    fun displayName(): String {
        val resId = when (this) {
            Doctor -> R.string.doctor
            Patient -> R.string.patient
        }

        return getLocalizedString(resId)
    }
}
