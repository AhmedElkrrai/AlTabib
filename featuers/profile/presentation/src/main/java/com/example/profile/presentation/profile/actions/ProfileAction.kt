package com.example.profile.presentation.profile.actions

import android.net.Uri
import com.example.user.domain.entities.Doctor

sealed class ProfileAction {
    data object InitViewState : ProfileAction()
    data class OnNameChange(val value: String) : ProfileAction()
    data class OnBioChange(val value: String) : ProfileAction()
    data class OnPriceChange(val value: String) : ProfileAction()
    data class OnCityChange(val value: String) : ProfileAction()
    data class OnAddressChange(val value: String) : ProfileAction()
    data class OnContactChange(val value: String) : ProfileAction()
    data class OnAvatarSelected(val uri: Uri, val doctor: Doctor) : ProfileAction()
    data class OnSpecializationSelected(val key: String) : ProfileAction()
    data class OnQueueChanged(val value: Int) : ProfileAction()
    data class OnSaveClick(val doctor: Doctor) : ProfileAction()
    data object OnSpecializationClick : ProfileAction()
    data object OnOpenImagePicker : ProfileAction()
    data object OnEditAvailabilityClick : ProfileAction()
    data object OnLogoutClick : ProfileAction()
    data object OnChangeLanguageClick : ProfileAction()
    data object OnContactDeveloperClick : ProfileAction()
}
