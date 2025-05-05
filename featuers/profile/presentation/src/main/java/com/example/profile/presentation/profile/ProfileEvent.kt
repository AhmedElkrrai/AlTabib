package com.example.profile.presentation.profile

import com.example.altabib.core.DataError

sealed interface ProfileEvent {
    data class ShowToast(val error: DataError) : ProfileEvent
    data class ShowMessage(val msgRes: Int) : ProfileEvent
    data object Logout : ProfileEvent
    data object LanguageChanged : ProfileEvent
    data object ContactDevs : ProfileEvent
    data class Navigate(val route: String) : ProfileEvent
    data object OpenImagePicker : ProfileEvent
    data object OpenSpecializationDialog : ProfileEvent
}
