package com.example.profile.presentation.availability

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.DataError
import com.example.altabib.core.onError
import com.example.altabib.core.onSuccess
import com.example.doctors.domain.usecases.GetDoctorUseCase
import com.example.doctors.domain.usecases.UpdateAvailabilityUseCase
import com.example.user.domain.entities.Availability
import com.example.user.domain.usecases.GetUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AvailabilityViewModel(
    private val getDoctorUseCase: GetDoctorUseCase,
    private val updateAvailability: UpdateAvailabilityUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AvailabilityState())
    val state = _state
        .onStart {
            initAvailabilityData()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(3000L),
            AvailabilityState()
        )

    private val _event = MutableSharedFlow<AvailabilityEvent>()
    val event = _event.asSharedFlow()

    fun onAction(action: AvailabilityAction) {
        when (action) {
            is AvailabilityAction.OnAvailabilityChange -> updateAvailability(action.value)
            is AvailabilityAction.Save -> saveAvailability(action.value)
            AvailabilityAction.Back -> dismiss()
        }
    }

    private fun initAvailabilityData() {
        viewModelScope.launch {
            val doctorId = getUserUseCase()?.uid ?: return@launch
            _state.update { it.copy(isLoading = true) }
            getDoctorUseCase(doctorId)
                .onSuccess { doctor ->
                    _state.update {
                        it.copy(
                            data = doctor.availability,
                            isLoading = false
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _event.emit(AvailabilityEvent.ShowToast(error))
                }
        }
    }

    private fun dismiss() {
        viewModelScope.launch {
            _event.emit(AvailabilityEvent.Back)
        }
    }

    private fun updateAvailability(availability: Availability) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    data = availability
                )
            }
        }
    }

    private fun saveAvailability(value: Availability) {
        viewModelScope.launch {
            updateAvailability.invoke(value)
                .onSuccess {
                    _event.emit(AvailabilityEvent.Back)
                }
                .onError {
                    _event.emit(
                        AvailabilityEvent.ShowToast(DataError.FailedToUpdateData)
                    )
                }
        }
    }
}
