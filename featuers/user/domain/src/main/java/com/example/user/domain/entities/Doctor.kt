package com.example.user.domain.entities

data class Doctor(
    val id: String = "",
    val avatar: String = "",
    val name: String = "",
    val specKey: String = "",
    val rating: Float = 0f,
    val reviews: Int = 0,
    val bio: String = "",
    val availability: Availability = Availability(),
    val inQueue: Int = 0,
    val price: Int = 0,
    val premium: Boolean = false,
    val address: String = "",
    val city: String = "",
    val reviewsList: List<Review> = emptyList(),
    val contact: String = ""
)
