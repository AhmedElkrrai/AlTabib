package com.example.altabib.featuers.settings.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.R
import com.example.altabib.core.DataError
import com.example.altabib.core.getOrDefault
import com.example.altabib.featuers.settings.domain.usecases.GetPatientUseCase
import com.example.altabib.featuers.settings.domain.usecases.UpdatePatientUseCase
import com.example.user.data.source.remote.mappers.toUser
import com.example.user.domain.usecases.CacheUserUseCase
import com.example.user.domain.usecases.LogoutUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val updateUserUseCase: CacheUserUseCase,
    private val getPatientUseCase: GetPatientUseCase,
    private val updatePatientUseCase: UpdatePatientUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state

    private val _event = MutableSharedFlow<SettingsEvent>()
    val event: SharedFlow<SettingsEvent> = _event

    fun onAction(action: SettingsAction) {
        when (action) {
            is SettingsAction.InitPatientData -> initPatientData()

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

            is SettingsAction.ChangeLanguage -> {
                viewModelScope.launch {
                    _event.emit(SettingsEvent.ChangeAppLanguage(action.language))
                }
            }
        }
    }

    private fun initPatientData() {
        viewModelScope.launch {
            try {
                val patient = getPatientUseCase
                    .invoke()
                    ?.getOrDefault(null) ?: return@launch

                _state.update { it.copy(patient = patient) }
            } catch (e: Exception) {
                Log.e("SettingsViewModel", "Error retrieving patient data", e)
                _event.emit(
                    SettingsEvent.ShowToast(DataError.FailedToRetrieveData)
                )
            }
        }
    }

    private fun changeCity(city: String) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(patient = state.patient?.copy(city = city))
            }
        }
    }

    private fun updateName(name: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(patient = it.patient?.copy(name = name))
            }
        }
    }

    private fun updateProfile() {
        viewModelScope.launch {
            try {
                _state.value.patient?.let { patient ->
                    updatePatientUseCase(patient)
                    updateUserUseCase(patient.toUser())
                }
                _event.emit(
                    SettingsEvent.ShowMessage(R.string.update_profile_msg)
                )
            } catch (e: Exception) {
                _event.emit(
                    SettingsEvent.ShowToast(
                        DataError.FailedToRetrieveData
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
                    SettingsEvent.ShowToast(DataError.GeneralError)
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
