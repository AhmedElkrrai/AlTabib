package com.example.altabib.featuers.settings.presentation

import com.example.altabib.core.domain.util.DataError

sealed interface SettingsEvent {
    data object LoggedOut : SettingsEvent
    data class ShowToast(val error: DataError) : SettingsEvent
    data class ShowMessage(val msgRes: Int) : SettingsEvent
    data object RateApp : SettingsEvent
    data object ContactDevs : SettingsEvent
}
