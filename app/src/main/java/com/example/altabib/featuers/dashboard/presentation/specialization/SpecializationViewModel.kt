package com.example.altabib.featuers.dashboard.presentation.specialization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.onError
import com.example.altabib.core.onSuccess
import com.example.altabib.featuers.dashboard.domain.usecases.GetDoctorsBySpecializationUseCase
import com.example.altabib.design_system.navigation.screen.PatientScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SpecializationViewModel(
    private val useCase: GetDoctorsBySpecializationUseCase,
) : ViewModel() {

    private val initialState = SpecializationState()

    private val _state = MutableStateFlow(initialState)
    val state = _state
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(3000L),
            initialState
        )

    private val _event = MutableSharedFlow<SpecializationEvent>()
    val event: SharedFlow<SpecializationEvent> = _event

    fun onAction(action: SpecializationAction) {
        when (action) {
            is SpecializationAction.LoadDoctors -> loadDoctors(action.specializationKey)

            is SpecializationAction.OnBackClick -> {
                viewModelScope.launch {
                    _event.emit(SpecializationEvent.Back)
                }
            }

            is SpecializationAction.OnDoctorClick -> {
                viewModelScope.launch {
                    _event.emit(
                        SpecializationEvent.Navigate(
                            PatientScreen.DoctorDetails.createRoute(
                                doctorId = action.doctor.id
                            )
                        )
                    )
                }
            }
        }
    }

    private fun loadDoctors(key: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = useCase(key)

            result
                .onSuccess { doctors ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            doctors = doctors
                        )
                    }
                }
                .onError {
                    _event.emit(SpecializationEvent.ShowToast(it))
                    _state.update { state -> state.copy(isLoading = false) }
                }
        }
    }
}
