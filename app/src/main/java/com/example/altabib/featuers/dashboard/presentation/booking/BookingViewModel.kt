package com.example.altabib.featuers.dashboard.presentation.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.design.R
import com.example.altabib.core.DataError
import com.example.altabib.core.onError
import com.example.altabib.core.onSuccess
import com.example.appointments.domain.usecases.SaveAppointmentUseCase
import com.example.doctors.domain.usecases.GetDoctorByIdUseCase
import com.example.doctors.domain.usecases.UpdateDoctorUseCase
import com.example.settings.domain.usecases.GetPatientUseCase
import com.example.settings.domain.usecases.UpdatePatientUseCase
import com.example.user.domain.entities.Patient
import com.example.user.domain.entities.Review
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import java.util.UUID

class BookingViewModel(
    private val getDoctorUseCase: GetDoctorByIdUseCase,
    private val updateDoctorUseCase: UpdateDoctorUseCase,
    private val updatePatientUseCase: UpdatePatientUseCase,
    private val getPatientUseCase: GetPatientUseCase,
    private val saveAppointmentUseCase: SaveAppointmentUseCase
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

            is BookingAction.OnDateSelected -> {
                _state.update { it.copy(selectedDate = action.date) }
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
                confirmBooking()
            }
        }
    }

    private fun loadDoctor(doctorId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val doctorResult = getDoctorUseCase(doctorId)
            val patientResult = getPatientUseCase()

            doctorResult.onSuccess { doctor ->
                var existingRating: Int? = null

                patientResult?.onSuccess { patient ->
                    existingRating = patient.ratings
                        .firstOrNull { it.doctorId == doctorId }
                        ?.rating
                }

                _state.update {
                    it.copy(
                        isLoading = false,
                        doctor = doctor,
                        userRating = existingRating ?: 0
                    )
                }
            }.onError {
                _event.emit(BookingEvent.ShowToast(it))
                _state.update { state -> state.copy(isLoading = false) }
            }
        }
    }

    private fun submitRating(rating: Int) {
        viewModelScope.launch {
            getPatientUseCase.invoke()?.onSuccess { patient ->
                val currentDoctor = _state.value.doctor
                currentDoctor?.let {
                    val newAverageRating =
                        ((it.rating * it.reviews) + rating) / (it.reviews + 1).toFloat()

                    val updatedDoctor = it.copy(
                        rating = newAverageRating,
                        reviews = it.reviews + 1
                    )

                    val patientRating = Patient.Rating(
                        doctorId = updatedDoctor.id,
                        rating = rating
                    )

                    val updatedPatient = patient.copy(
                        ratings = patient.ratings + patientRating
                    )

                    updatePatientUseCase(updatedPatient)
                        .onError { error ->
                            _event.emit(BookingEvent.ShowToast(error))
                        }

                    val result = updateDoctorUseCase(updatedDoctor)
                    result
                        .onSuccess {
                            _event.emit(BookingEvent.ShowMessage(R.string.rating_submitted_successfully))
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
    }

    private fun submitReview() {
        viewModelScope.launch {
            if (_state.value.userReview.isBlank()) {
                _event.emit(BookingEvent.ShowMessage(R.string.review_cannot_be_empty))
                return@launch
            }
            getPatientUseCase.invoke()?.onSuccess { patient ->
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
                            _event.emit(BookingEvent.ShowMessage(R.string.review_submitted_successfully))
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
    }

    private fun confirmBooking() {
        val selectedDate = state.value.selectedDate
        val doctor = state.value.doctor

        if (selectedDate == null) {
            viewModelScope.launch {
                _event.emit(BookingEvent.ShowMessage(R.string.select_date))
            }
            return
        }

        if (doctor == null) return

        viewModelScope.launch {
            getPatientUseCase.invoke()
                ?.onSuccess { patient ->
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val dateString = selectedDate.format(formatter)
                    val appointment = com.example.appointments.domain.entities.Appointment(
                        id = UUID.randomUUID().toString(),
                        doctorId = doctor.id,
                        patientId = patient.uid,
                        date = dateString
                    )

                    val result = saveAppointmentUseCase(appointment)

                    result
                        .onSuccess {
                            _event.emit(BookingEvent.ShowMessage(R.string.appointment_booked_successfully))
                            delay(2000)
                            _event.emit(BookingEvent.Back)
                        }
                        .onError {
                            _event.emit(BookingEvent.ShowToast(it))
                        }
                }
                ?.onError {
                    _event.emit(BookingEvent.ShowToast(DataError.GeneralError))
                }
        }
    }
}
