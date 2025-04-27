package com.example.doctors.data.source.remote.models

data class DoctorDto(
    val id: String = "",
    val name: String = "",
    val avatar: String = "",
    val specialization: String = "",
    val rating: Double = 0.0,
    val reviews: Int = 0,
    val bio: String = "",
    val availability: String = "",
    val inQueue: Int = 0,
    val price: Int = 0,
    val premium: Boolean = false,
    val address: String = "",
    val city: String = "",
    val reviewsList: List<ReviewDto> = emptyList()
)
