package com.example.altabib.core.models

data class Review(
    val id: String = "",
    val userName: String = "",
    val text: String = "",
    val rating: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
