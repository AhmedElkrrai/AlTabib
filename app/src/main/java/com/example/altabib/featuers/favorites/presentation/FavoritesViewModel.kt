package com.example.altabib.featuers.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.onError
import com.example.altabib.core.onSuccess
import com.example.user.domain.entities.Doctor
import com.example.favorites.domain.usecases.GetFavoritesUseCase
import com.example.favorites.domain.usecases.RemoveFavoriteUseCase
import com.example.altabib.design_system.navigation.screen.PatientScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesUseCase: com.example.favorites.domain.usecases.GetFavoritesUseCase,
    private val removeFavoriteUseCase: com.example.favorites.domain.usecases.RemoveFavoriteUseCase
) : ViewModel() {

    private val initialState = FavoritesState()
    private val _state: MutableStateFlow<FavoritesState> = MutableStateFlow(initialState)
    val state: StateFlow<FavoritesState> = _state
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(3000L),
            initialState
        )

    private val _event = MutableSharedFlow<FavoritesEvent>()
    val event: SharedFlow<FavoritesEvent> = _event

    fun onAction(action: FavoritesAction) {
        when (action) {
            is FavoritesAction.LoadFavorites -> loadFavorites()

            is FavoritesAction.OnDoctorClick -> {
                viewModelScope.launch {
                    _event.emit(
                        FavoritesEvent.Navigate(
                            PatientScreen.DoctorDetails.createRoute(
                                doctorId = action.doctorId
                            )
                        )
                    )
                }
            }

            is FavoritesAction.UnFavoriteDoctor -> unFavoriteDoctor(action.doctor)
        }
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result = getFavoritesUseCase()
            result
                .onSuccess { doctors ->
                    _state.update { state -> state.copy(isLoading = false, doctors = doctors) }
                }.onError {
                    _state.update { state -> state.copy(isLoading = false) }
                    _event.emit(FavoritesEvent.ShowToast(it))
                }
        }
    }

    private fun unFavoriteDoctor(doctor: Doctor) {
        viewModelScope.launch {
            val result = removeFavoriteUseCase(doctor)
            result
                .onSuccess {
                    _state.update { state ->
                        state.copy(
                            doctors = state.doctors.filter { it.id != doctor.id }
                        )
                    }
                }.onError {
                    _event.emit(FavoritesEvent.ShowToast(it))
                }
        }
    }
}
