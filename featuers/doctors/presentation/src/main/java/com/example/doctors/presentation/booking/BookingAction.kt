package com.example.doctors.presentation.booking

import java.time.LocalDate

sealed class BookingAction {
    data class LoadDoctor(val doctorId: String) : BookingAction()
    data object OnBackClick : BookingAction()
    data class OnDateSelected(val date: LocalDate) : BookingAction()
    data object OnConfirmBooking : BookingAction()
    data class OnReviewTextChanged(val text: String) : BookingAction()
    data class OnSubmitRating(val rating: Int) : BookingAction()
    data object OnSubmitReview : BookingAction()
}
