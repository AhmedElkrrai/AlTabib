package com.example.altabib.featuers.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.domain.util.DataError
import com.example.altabib.featuers.user.domain.usecases.LogoutUseCase
import com.example.altabib.navigation.screen.PatientScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
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
            SettingsAction.Logout -> logout()
            SettingsAction.EditProfile -> {
                viewModelScope.launch {
                    _event.emit(SettingsEvent.Navigate(PatientScreen.EditProfile.route))
                }
            }

            SettingsAction.ContactDevs -> {
                viewModelScope.launch {
                    _event.emit(SettingsEvent.Navigate(PatientScreen.ContactUs.route))
                }
            }

            SettingsAction.RateApp -> {
                viewModelScope.launch {
                    _event.emit(SettingsEvent.RateApp)
                }
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
}
