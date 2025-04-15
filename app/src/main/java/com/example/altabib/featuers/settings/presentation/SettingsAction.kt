package com.example.altabib.featuers.settings.presentation

sealed interface SettingsAction {
    data object Logout : SettingsAction
    data object EditProfile : SettingsAction
    data object ContactDevs : SettingsAction
    data object RateApp : SettingsAction
}
