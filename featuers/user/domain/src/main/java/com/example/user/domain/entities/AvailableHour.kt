package com.example.user.domain.entities

data class AvailableHour(
    val time: Int = 1,
    val period: Period = Period.PM
)