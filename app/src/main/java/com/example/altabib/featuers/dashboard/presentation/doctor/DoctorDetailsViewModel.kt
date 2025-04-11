package com.example.altabib.featuers.dashboard.presentation.doctor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.domain.util.onError
import com.example.altabib.core.domain.util.onSuccess
import com.example.altabib.featuers.dashboard.domain.usecases.GetDoctorByIdUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DoctorDetailsViewModel(
    private val useCase: GetDoctorByIdUseCase,
) : ViewModel() {
    private val initialState = DoctorDetailsState()
    private val _state: MutableStateFlow<DoctorDetailsState> = MutableStateFlow(initialState)
    val state: StateFlow<DoctorDetailsState> = _state
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(3000L),
            initialState
        )

    private val _event = MutableSharedFlow<DoctorDetailsEvent>()
    val event: SharedFlow<DoctorDetailsEvent> = _event

    fun onAction(action: DoctorDetailsAction) {
        when (action) {
            is DoctorDetailsAction.LoadDoctor -> loadDoctor(action.doctorId)

            is DoctorDetailsAction.OnBackClick -> {
                viewModelScope.launch {
                    _event.emit(DoctorDetailsEvent.Back)
                }
            }

            is DoctorDetailsAction.OnBookAppointmentClick -> {
                // Handle book appointment action
            }

            is DoctorDetailsAction.OnAddToFavoritesClick -> {
                // Handle add to favorites action
            }

            is DoctorDetailsAction.OnAddressClick -> {
                // Handle address click action
            }

            is DoctorDetailsAction.OnSubmitRating -> {
                // Handle submit rating action
            }
        }
    }

    private fun loadDoctor(doctorId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = useCase(doctorId)

            result
                .onSuccess { doctor ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            doctor = doctor
                        )
                    }
                }
                .onError {
                    _event.emit(DoctorDetailsEvent.ShowToast(it))
                    _state.update { state -> state.copy(isLoading = false) }
                }
        }
    }
}
