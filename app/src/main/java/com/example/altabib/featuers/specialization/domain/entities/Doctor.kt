package com.example.altabib.featuers.specialization.domain.entities

data class Doctor(
    val name: String,
    val specialization: Specialization,
    val rating: Float,
    val bio: String,
    val availability: String, // eg 06PM - 09PM
    val inQueue: Int,
    val price: Int,
)
