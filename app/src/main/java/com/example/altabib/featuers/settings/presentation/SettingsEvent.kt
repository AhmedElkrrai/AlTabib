package com.example.altabib.featuers.settings.presentation

import com.example.altabib.core.DataError
import com.example.altabib.design_system.LocaleHelper

sealed interface SettingsEvent {
    data object LoggedOut : SettingsEvent
    data class ShowToast(val error: DataError) : SettingsEvent
    data class ShowMessage(val msgRes: Int) : SettingsEvent
    data object RateApp : SettingsEvent
    data object ContactDevs : SettingsEvent
    data class ChangeAppLanguage(val language: LocaleHelper.Language) : SettingsEvent
}
