package com.example.settings.presentation

import com.example.altabib.core.DataError
import com.example.altabib.design_system.localization.LocaleHelper

sealed interface SettingsEvent {
    data object LoggedOut : SettingsEvent
    data class ShowToast(val error: DataError) : SettingsEvent
    data class ShowMessage(val msgRes: Int) : SettingsEvent
    data object RateApp : SettingsEvent
    data object ContactDevs : SettingsEvent
    data class RestartAppForLanguageChange(val language: LocaleHelper.Language) : SettingsEvent
}
