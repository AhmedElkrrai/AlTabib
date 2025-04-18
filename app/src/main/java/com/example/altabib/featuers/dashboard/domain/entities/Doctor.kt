package com.example.altabib.featuers.dashboard.domain.entities

data class Doctor(
    val id: String = "",
    val avatar: String = "",
    val name: String = "",
    val specialization: Specialization = Specialization.GENERAL_PRACTICE,
    val rating: Float = 0f,
    val reviews: Int = 0,
    val bio: String = "",
    val availability: String = "", // eg 06PM - 09PM
    val inQueue: Int = 0,
    val price: Int = 0,
    val premium: Boolean = false,
    val address: String = "",
    val city: String = "",
    val reviewsList: List<Review> = emptyList()
)
