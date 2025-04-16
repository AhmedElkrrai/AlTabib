package com.example.altabib.featuers.settings.presentation

import com.example.altabib.core.domain.util.DataError

data class SettingsState(
    val name: String = "",
    val city: String = "",
    val loading: Boolean = false,
    val error: DataError? = null
)
