package com.example.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.DataError
import com.example.altabib.core.onError
import com.example.altabib.core.onSuccess
import com.example.doctors.domain.usecases.GetDoctorUseCase
import com.example.doctors.domain.usecases.UpdateDoctorUseCase
import com.example.user.domain.usecases.GetUserUseCase
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
            else -> {}
        }
    }

    private fun initDoctorData() {
        viewModelScope.launch {
            val doctorId = getUserUseCase()?.uid ?: return@launch
            getDoctorUseCase(doctorId)
                .onSuccess {
                    _state.update { state ->
                        state.copy(
                            doctor = it
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