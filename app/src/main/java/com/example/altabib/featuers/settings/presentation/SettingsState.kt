package com.example.altabib.featuers.settings.presentation

import com.example.altabib.core.domain.util.DataError

data class SettingsState(
    val isLoading: Boolean = false,
    val error: DataError? = null
)
