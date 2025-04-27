package com.example.doctors.data.source.remote.models

data class ReviewDto(
    val id: String = "",
    val userName: String = "",
    val text: String = "",
    val rating: Int = 0,
    val createdAt: Long = 0
)
