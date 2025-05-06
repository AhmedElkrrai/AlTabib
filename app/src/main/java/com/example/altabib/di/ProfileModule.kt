package com.example.altabib.di

import com.example.profile.presentation.profile.actions.InitViewStateAction
import com.example.profile.presentation.profile.actions.OnAddressChangeAction
import com.example.profile.presentation.profile.actions.OnAvatarSelected
import com.example.profile.presentation.profile.actions.OnBioChangeAction
import com.example.profile.presentation.profile.actions.OnChangeLanguageAction
import com.example.profile.presentation.profile.actions.OnCityChangeAction
import com.example.profile.presentation.profile.actions.OnContactChangeAction
import com.example.profile.presentation.profile.actions.OnContactDeveloperAction
import com.example.profile.presentation.profile.actions.OnEditAvailabilityAction
import com.example.profile.presentation.profile.actions.OnLogoutAction
import com.example.profile.presentation.profile.actions.OnNameChangeAction
import com.example.profile.presentation.profile.actions.OnOpenImagePickerAction
import com.example.profile.presentation.profile.actions.OnPriceChangeAction
import com.example.profile.presentation.profile.actions.OnQueueChangedAction
import com.example.profile.presentation.profile.actions.OnSaveAction
import com.example.profile.presentation.profile.actions.OnSpecializationAction
import com.example.profile.presentation.profile.actions.OnSpecializationSelectedAction
import com.example.profile.presentation.profile.actions.ProfileActionTransformer
import org.koin.dsl.module

val profileModule = module {

    factory { OnAddressChangeAction() }
    factory { OnBioChangeAction() }
    factory { OnCityChangeAction() }
    factory { OnContactChangeAction() }
    factory { OnNameChangeAction() }
    factory { OnPriceChangeAction() }
    factory { OnQueueChangedAction() }
    factory { OnSpecializationSelectedAction() }
    factory { OnChangeLanguageAction() }
    factory { OnContactDeveloperAction() }
    factory { OnEditAvailabilityAction() }
    factory { OnOpenImagePickerAction() }
    factory { OnSpecializationAction() }

    factory { OnLogoutAction(get()) }
    factory { OnSaveAction(get()) }

    factory { OnAvatarSelected(get(), get(), get()) }

    factory { InitViewStateAction(get(), get(), get()) }

    factory {
        ProfileActionTransformer(
            onAddressChange = get(),
            onBioChange = get(),
            onCityChange = get(),
            onContactChange = get(),
            onNameChange = get(),
            onPriceChange = get(),
            onQueueChanged = get(),
            onSpecializationSelected = get(),
            onChangeLanguage = get(),
            onContactDeveloper = get(),
            onEditAvailability = get(),
            onOpenImagePicker = get(),
            onSpecializationClicked = get(),
            onLogout = get(),
            onSaveClicked = get(),
            onAvatarSelected = get(),
            initViewState = get()
        )
    }
}
