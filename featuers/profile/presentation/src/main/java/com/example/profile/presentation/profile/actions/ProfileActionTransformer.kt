package com.example.profile.presentation.profile.actions

import com.example.profile.presentation.profile.state.ProfileReducer
import kotlinx.coroutines.flow.Flow

class ProfileActionTransformer(
    private val onAddressChange: OnAddressChangeAction,
    private val onBioChange: OnBioChangeAction,
    private val onCityChange: OnCityChangeAction,
    private val onContactChange: OnContactChangeAction,
    private val onNameChange: OnNameChangeAction,
    private val onPriceChange: OnPriceChangeAction,
    private val onQueueChanged: OnQueueChangedAction,
    private val onSpecializationSelected: OnSpecializationSelectedAction,
    private val onChangeLanguage: OnChangeLanguageAction,
    private val onContactDeveloper: OnContactDeveloperAction,
    private val onEditAvailability: OnEditAvailabilityAction,
    private val onOpenImagePicker: OnOpenImagePickerAction,
    private val onSpecializationClicked: OnSpecializationAction,
    private val onLogout: OnLogoutAction,
    private val onSaveClicked: OnSaveAction,
    private val onAvatarSelected: OnAvatarSelected,
    private val initViewState: InitViewStateAction,
) {
    fun transform(
        action: ProfileAction
    ): Flow<ProfileReducer> {
        return when (action) {
            is ProfileAction.InitViewState -> initViewState.invoke()
            is ProfileAction.OnAddressChange -> onAddressChange.invoke(action.value)
            is ProfileAction.OnBioChange -> onBioChange.invoke(action.value)
            is ProfileAction.OnCityChange -> onCityChange.invoke(action.value)
            is ProfileAction.OnContactChange -> onContactChange.invoke(action.value)
            is ProfileAction.OnNameChange -> onNameChange.invoke(action.value)
            is ProfileAction.OnPriceChange -> onPriceChange.invoke(action.value)
            is ProfileAction.OnQueueChanged -> onQueueChanged.invoke(action.value)
            is ProfileAction.OnSpecializationSelected -> onSpecializationSelected.invoke(action.key)
            is ProfileAction.OnChangeLanguageClick -> onChangeLanguage.invoke()
            is ProfileAction.OnContactDeveloperClick -> onContactDeveloper.invoke()
            is ProfileAction.OnEditAvailabilityClick -> onEditAvailability.invoke()
            is ProfileAction.OnOpenImagePicker -> onOpenImagePicker.invoke()
            is ProfileAction.OnSpecializationClick -> onSpecializationClicked.invoke()
            is ProfileAction.OnSaveClick -> onSaveClicked.invoke(action.doctor)
            is ProfileAction.OnLogoutClick -> onLogout.invoke()
            is ProfileAction.OnAvatarSelected -> onAvatarSelected.invoke(action.uri, action.doctor)
        }
    }
}
