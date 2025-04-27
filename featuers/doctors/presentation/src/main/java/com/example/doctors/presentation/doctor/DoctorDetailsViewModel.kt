package com.example.doctors.presentation.doctor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.design.R
import com.example.altabib.core.onError
import com.example.altabib.core.onSuccess
import com.example.doctors.domain.usecases.GetDoctorByIdUseCase
import com.example.altabib.design_system.navigation.screen.PatientScreen
import com.example.favorites.domain.usecases.AddFavoriteUseCase
import com.example.favorites.domain.usecases.IsFavoriteUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DoctorDetailsViewModel(
    private val getDoctorUseCase: GetDoctorByIdUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
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
                viewModelScope.launch {
                    _event.emit(
                        DoctorDetailsEvent.Navigate(
                            PatientScreen.Booking.createRoute(
                                doctorId = action.doctorId
                            )
                        )
                    )
                }
            }

            is DoctorDetailsAction.OnAddToFavoritesClick -> {
                addToFavorites()
            }

            is DoctorDetailsAction.OnAddressClick -> {
                viewModelScope.launch {
                    _event.emit(DoctorDetailsEvent.NavigateToAddress(action.address))
                }
            }
        }
    }

    private fun loadDoctor(doctorId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = getDoctorUseCase(doctorId)

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

    private fun addToFavorites() {
        viewModelScope.launch {
            val doctor = state.value.doctor ?: return@launch
            val result = isFavoriteUseCase(doctor.id)
            if (result) {
                _event.emit(DoctorDetailsEvent.ShowMessage(R.string.doctor_already_added))
            } else {
                addFavoriteUseCase.invoke(state.value.doctor!!)
                    .onSuccess {
                        _event.emit(DoctorDetailsEvent.ShowMessage(R.string.doctor_added_to_favorites))
                    }
                    .onError {
                        _event.emit(DoctorDetailsEvent.ShowToast(it))
                    }
            }
        }
    }
}
