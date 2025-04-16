package com.example.altabib.featuers.settings.presentation

sealed interface SettingsAction {
    data object Logout : SettingsAction
    data object UpdateProfile : SettingsAction
    data object ContactDevs : SettingsAction
    data object RateApp : SettingsAction
    data class ChangeCity(val city: String) : SettingsAction
    data class UpdateName(val name: String) : SettingsAction
}
