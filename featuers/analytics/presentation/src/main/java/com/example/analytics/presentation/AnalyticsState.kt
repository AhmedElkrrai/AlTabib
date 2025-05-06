package com.example.analytics.presentation

import com.example.analytics.domain.entites.ProfileView
import com.example.altabib.core.models.Review

data class AnalyticsState(
    val isLoading: Boolean = false,
    val profile: ProfileView = ProfileView(),
    val rating: Float = 0f,
    val reviews: Int = 0,
    val reviewList: List<Review> = emptyList(),
)