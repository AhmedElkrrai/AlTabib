package com.example.altabib.featuers.dashboard.presentation.booking

sealed class BookingAction {
    data class LoadDoctor(val doctorId: String) : BookingAction()
    data object OnBackClick : BookingAction()
    data class OnConfirmBooking(val date: String) : BookingAction()
    data class OnReviewTextChanged(val text: String) : BookingAction()
    data class OnSubmitRating(val rating: Int) : BookingAction()
    data object OnSubmitReview : BookingAction()
}
