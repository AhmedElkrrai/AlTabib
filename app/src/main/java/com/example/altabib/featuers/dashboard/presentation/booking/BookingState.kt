package com.example.altabib.featuers.dashboard.presentation.booking

import com.example.altabib.featuers.dashboard.domain.entities.Doctor

data class BookingState(
    val doctor: Doctor? = null,
    val userReview: String = "",
    val userRating: Int = 0,
    val isLoading: Boolean = false,
)
