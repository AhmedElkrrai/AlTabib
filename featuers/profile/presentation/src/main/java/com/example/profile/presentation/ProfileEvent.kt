package com.example.profile.presentation

import com.example.altabib.core.DataError

sealed interface ProfileEvent {
    data class ShowToast(val error: DataError) : ProfileEvent
    data class ShowMessage(val msgRes: Int) : ProfileEvent
    data object Logout : ProfileEvent
    data object LanguageChanged : ProfileEvent
    data object ContactDevs : ProfileEvent
    data object EditAvailability : ProfileEvent
    data object OpenImagePicker : ProfileEvent
    data object OpenSpecializationDialog : ProfileEvent
}
