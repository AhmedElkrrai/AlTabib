package com.example.altabib.featuers.settings.presentation

import com.example.altabib.core.domain.util.DataError

sealed class SettingsEvent {
    data object LoggedOut : SettingsEvent()
    data class ShowToast(val error: DataError) : SettingsEvent()
}

