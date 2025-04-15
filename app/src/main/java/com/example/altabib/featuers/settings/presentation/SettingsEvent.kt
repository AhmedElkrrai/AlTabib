package com.example.altabib.featuers.settings.presentation

import com.example.altabib.core.domain.util.DataError

sealed interface SettingsEvent {
    data object LoggedOut : SettingsEvent
    data class ShowToast(val error: DataError) : SettingsEvent
    data class Navigate(val route: String) : SettingsEvent
    data object RateApp : SettingsEvent
}
