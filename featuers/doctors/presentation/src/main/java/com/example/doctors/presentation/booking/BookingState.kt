package com.example.doctors.presentation.booking

import com.example.user.domain.entities.Doctor
import java.time.LocalDate

data class BookingState(
    val doctor: Doctor? = null,
    val userReview: String = "",
    val userRating: Int = 0,
    val selectedDate: LocalDate? = null,
    val isLoading: Boolean = false,
)
