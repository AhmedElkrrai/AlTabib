package com.example.settings.presentation

import com.example.altabib.design_system.localization.LocaleHelper

sealed interface SettingsAction {
    data object InitPatientData : SettingsAction
    data object Logout : SettingsAction
    data object UpdateProfile : SettingsAction
    data object ContactDevs : SettingsAction
    data object RateApp : SettingsAction
    data class ChangeCity(val city: String) : SettingsAction
    data class UpdateName(val name: String) : SettingsAction
    data class ChangeLanguage(val language: LocaleHelper.Language) : SettingsAction
}
