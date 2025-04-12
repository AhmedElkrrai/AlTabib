package com.example.altabib.featuers.dashboard.presentation.booking

import com.example.altabib.featuers.dashboard.domain.entities.Review

sealed class BookingAction {
    data object OnBackClick : BookingAction()
    data class OnSubmitReview(val doctorId: String, val review: Review) : BookingAction()
    data class OnReviewTextChanged(val text: String) : BookingAction()
    data class OnRatingChanged(val rating: Int) : BookingAction()
}
