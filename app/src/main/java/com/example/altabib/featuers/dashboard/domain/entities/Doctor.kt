package com.example.altabib.featuers.dashboard.domain.entities

data class Doctor(
    val id: String,
    val name: String,
    val specialization: Specialization,
    val rating: Float,
    val reviews: Int,
    val bio: String,
    val availability: String, // eg 06PM - 09PM
    val inQueue: Int,
    val price: Int,
    val premium: Boolean,
    val address: String,
)
