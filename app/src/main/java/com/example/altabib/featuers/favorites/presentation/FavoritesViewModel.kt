package com.example.altabib.featuers.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.domain.util.onError
import com.example.altabib.core.domain.util.onSuccess
import com.example.altabib.featuers.favorites.domain.usecases.GetFavoritesUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase,
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
        }
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val result = getFavoritesUseCase()
            result
                .onSuccess { doctors ->
                    _state.update { state -> state.copy(isLoading = false, favorites = doctors) }
                }.onError {
                    _state.update { state -> state.copy(isLoading = false) }
                    _event.emit(FavoritesEvent.ShowToast(it))
                }
        }
    }
}
