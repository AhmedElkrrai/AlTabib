package com.example.altabib.featuers.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.domain.util.DataError
import com.example.altabib.featuers.user.domain.usecases.LogoutUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state

    private val _event = MutableSharedFlow<SettingsEvent>()
    val event: SharedFlow<SettingsEvent> = _event

    fun onAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.Logout -> logout()

            is SettingsAction.UpdateProfile -> updateProfile()

            is SettingsAction.ContactDevs -> {
                sendEvent(SettingsEvent.ContactDevs)
            }

            is SettingsAction.RateApp -> {
                sendEvent(SettingsEvent.RateApp)
            }

            is SettingsAction.ChangeCity -> changeCity(action.city)

            is SettingsAction.UpdateName -> updateName(action.name)
        }
    }

    private fun changeCity(city: String) {
        viewModelScope.launch {
            _state.update { it.copy(city = city) }
        }
    }

    private fun updateName(name: String) {
        viewModelScope.launch {
            _state.update { it.copy(name = name) }
        }
    }

    private fun updateProfile() {
        viewModelScope.launch {
            try {
                // Update profile logic here
            } catch (e: Exception) {
                _event.emit(
                    SettingsEvent.ShowToast(
                        DataError.RetrievalError("Update profile failed: ${e.message}")
                    )
                )
            }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            try {
                logoutUseCase.invoke()
                _event.emit(SettingsEvent.LoggedOut)
            } catch (e: Exception) {
                _event.emit(
                    SettingsEvent.ShowToast(
                        DataError.RetrievalError("Logout failed: ${e.message}")
                    )
                )
            }
        }
    }

    private fun sendEvent(event: SettingsEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}
