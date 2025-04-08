package com.example.altabib.featuers.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.domain.util.DataError
import com.example.altabib.featuers.user.domain.AuthRepository
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
            SettingsAction.Logout -> logout()
        }
    }

    private fun logout() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            try {
                logoutUseCase.invoke()
                _event.emit(SettingsEvent.LoggedOut)
            } catch (e: Exception) {
                _event.emit(
                    SettingsEvent.ShowToast(
                        DataError.AuthError.RetrievalError("Logout failed: ${e.message}")
                    )
                )
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }
}
