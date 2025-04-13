package com.example.altabib.featuers.dashboard.presentation.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.domain.util.onError
import com.example.altabib.core.domain.util.onSuccess
import com.example.altabib.featuers.dashboard.domain.entities.Review
import com.example.altabib.featuers.dashboard.domain.usecases.GetDoctorByIdUseCase
import com.example.altabib.featuers.dashboard.domain.usecases.UpdateDoctorUseCase
import com.example.altabib.featuers.user.domain.usecases.GetUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookingViewModel(
    private val getDoctorUseCase: GetDoctorByIdUseCase,
    private val updateDoctorUseCase: UpdateDoctorUseCase,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(BookingState())
    val state: StateFlow<BookingState> = _state

    private val _event = MutableSharedFlow<BookingEvent>()
    val event: SharedFlow<BookingEvent> = _event

    fun onAction(action: BookingAction) {
        when (action) {
            is BookingAction.LoadDoctor -> loadDoctor(action.doctorId)

            is BookingAction.OnBackClick -> {
                viewModelScope.launch {
                    _event.emit(BookingEvent.Back)
                }
            }

            is BookingAction.OnReviewTextChanged -> {
                _state.update { it.copy(userReview = action.text) }
            }

            is BookingAction.OnSubmitRating -> {
                submitRating(action.rating)
            }

            is BookingAction.OnSubmitReview -> {
                submitReview()
            }

            is BookingAction.OnConfirmBooking -> {
                confirmBooking(action.review)
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
                    _event.emit(BookingEvent.ShowToast(it))
                    _state.update { state -> state.copy(isLoading = false) }
                }
        }
    }

    private fun submitRating(rating: Int) {
        viewModelScope.launch {
            val currentDoctor = _state.value.doctor
            currentDoctor?.let {
                val newAverageRating =
                    ((it.rating * it.reviews) + rating) / (it.reviews + 1).toFloat()

                val updatedDoctor = it.copy(
                    rating = newAverageRating,
                    reviews = it.reviews + 1
                )

                val result = updateDoctorUseCase(updatedDoctor)
                result
                    .onSuccess {
                        _event.emit(BookingEvent.ShowMessage("Rating submitted successfully"))
                        _state.update { state ->
                            state.copy(
                                doctor = updatedDoctor,
                                userRating = rating,
                            )
                        }
                    }
                    .onError { error ->
                        _event.emit(BookingEvent.ShowToast(error))
                    }
            }
        }
    }

    private fun submitReview() {
        viewModelScope.launch {
            if (_state.value.userReview.isBlank()) {
                _event.emit(BookingEvent.ShowMessage("Review cannot be empty"))
                return@launch
            }
            val patient = getUserUseCase() ?: return@launch

            val review = Review(
                id = patient.uid,
                userName = patient.name,
                text = _state.value.userReview,
                rating = _state.value.userRating
            )

            val currentDoctor = _state.value.doctor

            currentDoctor?.let {
                val updatedDoctor = it.copy(
                    reviewsList = it.reviewsList + review
                )
                val result = updateDoctorUseCase(updatedDoctor)
                result
                    .onSuccess {
                        _event.emit(BookingEvent.ShowMessage("Review submitted successfully"))
                        _state.update { state ->
                            state.copy(
                                doctor = updatedDoctor,
                                userReview = "",
                            )
                        }
                    }
                    .onError { error ->
                        _event.emit(BookingEvent.ShowToast(error))
                    }
            }
        }
    }

    private fun confirmBooking(review: Review) {

    }
}
