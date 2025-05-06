package com.example.profile.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.design_system.navigation.screen.DoctorScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val reducer: ProfileReducer,
    private val eventHandler: ProfileEventHandler
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state
        .onStart {
            eventHandler.initDoctorData(
                updateState = { doctor ->
                    _state.update { it.copy(doctor = doctor) }
                },
                emitEvent = { _event.emit(it) }
            )
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(3000L),
            ProfileState()
        )

    private val _event = MutableSharedFlow<ProfileEvent>()
    val event = _event.asSharedFlow()

    fun onAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.OnAddressChange,
            is ProfileAction.OnBioChange,
            is ProfileAction.OnCityChange,
            is ProfileAction.OnContactChange,
            is ProfileAction.OnNameChange,
            is ProfileAction.OnPriceChange,
            is ProfileAction.OnSpecializationSelected,
            is ProfileAction.OnQueueChanged -> {
                _state.update { reducer.reduce(it, action) }
            }

            is ProfileAction.OnAvatarSelected -> {
                viewModelScope.launch {
                    eventHandler.handleAvatarSelected(
                        uri = action.uri,
                        currentDoctor = state.value.doctor,
                        emitEvent = { _event.emit(it) },
                        updateState = { url ->
                            _state.update { it.copy(doctor = it.doctor.copy(avatar = url)) }
                        }
                    )
                }
            }

            is ProfileAction.OnSaveClick -> {
                viewModelScope.launch {
                    eventHandler.saveProfile(
                        doctor = state.value.doctor,
                        emitEvent = { _event.emit(it) }
                    )
                }
            }

            is ProfileAction.OnLogoutClick -> {
                viewModelScope.launch {
                    eventHandler.logout { _event.emit(it) }
                }
            }

            is ProfileAction.OnChangeLanguageClick -> emit(ProfileEvent.LanguageChanged)
            is ProfileAction.OnContactDeveloperClick -> emit(ProfileEvent.ContactDevs)
            is ProfileAction.OnEditAvailabilityClick -> emit(
                ProfileEvent.Navigate(DoctorScreen.EditAvailability.route)
            )

            is ProfileAction.OnOpenImagePicker -> emit(ProfileEvent.OpenImagePicker)
            is ProfileAction.OnSpecializationClick -> emit(ProfileEvent.OpenSpecializationDialog)
        }
    }

    private fun emit(event: ProfileEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}