package com.example.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.DataError
import com.example.altabib.core.LocalImageStorage
import com.example.altabib.core.onError
import com.example.altabib.core.onSuccess
import com.example.altabib.design.R
import com.example.altabib.design_system.utils.ByteArrayConverter
import com.example.doctors.domain.usecases.GetDoctorUseCase
import com.example.doctors.domain.usecases.UpdateAvatarUseCase
import com.example.doctors.domain.usecases.UpdateDoctorUseCase
import com.example.user.domain.usecases.GetUserUseCase
import com.example.user.domain.usecases.LogoutUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val getDoctorUseCase: GetDoctorUseCase,
    private val updateDoctorUseCase: UpdateDoctorUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val updateAvatarUseCase: UpdateAvatarUseCase,
    private val byteArrayConverter: ByteArrayConverter,
    private val imageStorage: LocalImageStorage
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = _state
        .onStart {
            initDoctorData()
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
            is ProfileAction.OnAddressChange -> updateAddress(action)
            is ProfileAction.OnBioChange -> onBioChange(action)
            is ProfileAction.OnCityChange -> onCityChange(action)
            is ProfileAction.OnContactChange -> onContactChange(action)
            is ProfileAction.OnNameChange -> onNameChange(action)
            is ProfileAction.OnPriceChange -> onPriceChange(action)
            is ProfileAction.OnSaveClick -> saveProfile()
            is ProfileAction.OnLogoutClick -> logout()
            is ProfileAction.OnChangeLanguageClick -> changeLanguage()
            is ProfileAction.OnContactDeveloperClick -> showContactDevsDialog()
            is ProfileAction.OnEditAvailabilityClick -> showEditAvailabilityDialog()
            is ProfileAction.OnOpenImagePicker -> openImagePicker()
            is ProfileAction.OnAvatarSelected -> onAvatarSelected(action)
            is ProfileAction.OnSpecializationClick -> openSpecializationDialog()
            is ProfileAction.OnSpecializationSelected -> onSpecializationSelected(action)
            is ProfileAction.OnQueueChanged -> onQueueChanged(action)
            is ProfileAction.OnAvailabilityChanged -> onAvailabilityChanged(action)
        }
    }

    private fun updateAddress(action: ProfileAction.OnAddressChange) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    doctor = state.doctor.copy(
                        address = action.value
                    )
                )
            }
        }
    }

    private fun onBioChange(action: ProfileAction.OnBioChange) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    doctor = state.doctor.copy(
                        bio = action.value
                    )
                )
            }
        }
    }

    private fun onCityChange(action: ProfileAction.OnCityChange) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    doctor = state.doctor.copy(
                        city = action.value
                    )
                )
            }
        }
    }

    private fun onContactChange(action: ProfileAction.OnContactChange) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    doctor = state.doctor.copy(
                        contact = action.value
                    )
                )
            }
        }
    }

    private fun onNameChange(action: ProfileAction.OnNameChange) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    doctor = state.doctor.copy(
                        name = action.value
                    )
                )
            }
        }
    }

    private fun onPriceChange(action: ProfileAction.OnPriceChange) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    doctor = state.doctor.copy(
                        price = Integer.parseInt(action.value)
                    )
                )
            }
        }
    }

    private fun onSpecializationSelected(action: ProfileAction.OnSpecializationSelected) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    doctor = state.doctor.copy(
                        specKey = action.specKey
                    )
                )
            }
        }
    }

    private fun showContactDevsDialog() {
        viewModelScope.launch {
            _event.emit(ProfileEvent.ContactDevs)
        }
    }

    private fun showEditAvailabilityDialog() {
        viewModelScope.launch {
            _event.emit(ProfileEvent.EditAvailability)
        }
    }

    private fun openImagePicker() {
        viewModelScope.launch {
            _event.emit(ProfileEvent.OpenImagePicker)
        }
    }

    private fun openSpecializationDialog() {
        viewModelScope.launch {
            _event.emit(ProfileEvent.OpenSpecializationDialog)
        }
    }

    private fun onAvatarSelected(action: ProfileAction.OnAvatarSelected) {
        viewModelScope.launch {
            val user = getUserUseCase() ?: return@launch

            val bytes = byteArrayConverter.uriToBytes(action.uri)
            if (bytes == null) {
                _event.emit(ProfileEvent.ShowToast(DataError.GeneralError))
                return@launch
            }

            updateAvatarUseCase.invoke(
                uploadToServer = false,
                doctorId = user.uid,
                bytes = bytes,
                oldAvatar = state.value.doctor.avatar
            )
                .onSuccess { url ->
                    _state.update { state ->
                        state.copy(
                            doctor = state.doctor.copy(
                                avatar = url
                            )
                        )
                    }
                }
                .onError {
                    _event.emit(ProfileEvent.ShowToast(DataError.FailedToUpdateData))
                }
        }
    }

    private fun onQueueChanged(action: ProfileAction.OnQueueChanged) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    doctor = state.doctor.copy(
                        inQueue = action.value
                    )
                )
            }
        }
    }

    private fun onAvailabilityChanged(action: ProfileAction.OnAvailabilityChanged) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    doctor = state.doctor.copy(
                        availability = action.value
                    )
                )
            }

            saveProfile()
        }
    }

    private fun logout() {
        viewModelScope.launch {
            try {
                logoutUseCase.invoke()
                _event.emit(ProfileEvent.Logout)
            } catch (e: Exception) {
                _event.emit(
                    ProfileEvent.ShowToast(DataError.GeneralError)
                )
            }
        }
    }

    private fun changeLanguage() {
        viewModelScope.launch {
            _event.emit(ProfileEvent.LanguageChanged)
        }
    }

    private fun saveProfile() {
        viewModelScope.launch {
            updateDoctorUseCase(state.value.doctor)
                .onSuccess {
                    _event.emit(
                        ProfileEvent.ShowMessage(R.string.update_profile_msg)
                    )
                }
                .onError {
                    _event.emit(
                        ProfileEvent.ShowToast(DataError.FailedToUpdateData)
                    )
                }
        }
    }

    private fun initDoctorData() {
        viewModelScope.launch {
            val doctorId = getUserUseCase()?.uid ?: return@launch
            getDoctorUseCase(doctorId)
                .onSuccess { doctor ->
                    _state.update { state ->
                        state.copy(
                            doctor = doctor.copy(
                                avatar = imageStorage.getCachedAvatarPath()
                            )
                        )
                    }
                }
                .onError {
                    _event.emit(
                        ProfileEvent.ShowToast(DataError.FailedToRetrieveData)
                    )
                }
        }
    }
}