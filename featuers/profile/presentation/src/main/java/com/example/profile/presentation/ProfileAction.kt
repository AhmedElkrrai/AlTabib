package com.example.profile.presentation

import android.net.Uri

sealed class ProfileAction {
    data class OnNameChange(val value: String) : ProfileAction()
    data class OnBioChange(val value: String) : ProfileAction()
    data class OnPriceChange(val value: String) : ProfileAction()
    data class OnCityChange(val value: String) : ProfileAction()
    data class OnAddressChange(val value: String) : ProfileAction()
    data class OnContactChange(val value: String) : ProfileAction()
    data class OnAvatarSelected(val uri: Uri) : ProfileAction()
    data class OnSpecializationSelected(val specKey: String) : ProfileAction()
    data object OnSpecializationClick : ProfileAction()
    data object OnOpenImagePicker : ProfileAction()
    data object OnEditAvailabilityClick : ProfileAction()
    data object OnSaveClick : ProfileAction()
    data object OnLogoutClick : ProfileAction()
    data object OnChangeLanguageClick : ProfileAction()
    data object OnContactDeveloperClick : ProfileAction()
}
