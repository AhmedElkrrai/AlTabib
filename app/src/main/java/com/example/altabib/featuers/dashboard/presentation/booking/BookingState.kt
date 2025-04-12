package com.example.altabib.featuers.dashboard.presentation.booking

import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.featuers.dashboard.domain.entities.Review

data class BookingState(
    val doctor: Doctor? = null,
    val reviews: List<Review> = emptyList(),
    val userReview: String = "",
    val userRating: Int = 0,
    val isLoading: Boolean = false,
)
