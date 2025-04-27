package com.example.settings.presentation

import com.example.altabib.core.DataError
import com.example.user.domain.entities.Patient

data class SettingsState(
    val patient: Patient? = null,
    val loading: Boolean = false,
    val error: DataError? = null
)
