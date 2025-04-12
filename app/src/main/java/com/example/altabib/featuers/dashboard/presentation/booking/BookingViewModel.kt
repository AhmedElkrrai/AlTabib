package com.example.altabib.featuers.dashboard.presentation.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookingViewModel(

) : ViewModel() {
    private val _state = MutableStateFlow(BookingState())
    val state: StateFlow<BookingState> = _state

    private val _event = MutableSharedFlow<BookingEvent>()
    val event: SharedFlow<BookingEvent> = _event

    fun onAction(action: BookingAction) {
        when (action) {
            is BookingAction.OnBackClick -> {
                viewModelScope.launch {
                    _event.emit(BookingEvent.Back)
                }
            }

            is BookingAction.OnReviewTextChanged -> {

            }

            is BookingAction.OnSubmitReview -> {

            }

            is BookingAction.OnRatingChanged -> {

            }
        }
    }
}
