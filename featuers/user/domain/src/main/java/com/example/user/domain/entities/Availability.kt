package com.example.user.domain.entities

data class Availability(
    val days: List<DayOfWeek> = emptyList(),
    val hours: List<TimeWindow> = emptyList()
)
