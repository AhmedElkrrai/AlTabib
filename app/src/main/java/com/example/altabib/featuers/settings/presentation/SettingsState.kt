package com.example.altabib.featuers.settings.presentation

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.featuers.settings.domain.entities.Patient

data class SettingsState(
    val patient: Patient? = null,
    val loading: Boolean = false,
    val error: DataError? = null
)
