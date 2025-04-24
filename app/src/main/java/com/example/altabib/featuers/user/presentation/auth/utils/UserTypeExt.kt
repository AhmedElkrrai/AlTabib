package com.example.altabib.featuers.user.presentation.auth.utils

import androidx.compose.runtime.Composable
import com.example.altabib.R
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.featuers.user.domain.entities.UserType
import com.example.altabib.featuers.user.domain.entities.UserType.Doctor
import com.example.altabib.featuers.user.domain.entities.UserType.Patient

@Composable
fun UserType.displayName(): String {
    val resId = when (this) {
        Doctor -> R.string.doctor
        Patient -> R.string.patient
    }

    return getLocalizedString(resId)
}