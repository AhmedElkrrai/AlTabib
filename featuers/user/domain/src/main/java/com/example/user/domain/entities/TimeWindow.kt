package com.example.user.domain.entities

data class TimeWindow(
    val start: AvailableHour = AvailableHour(),
    val end: AvailableHour = AvailableHour(),
)