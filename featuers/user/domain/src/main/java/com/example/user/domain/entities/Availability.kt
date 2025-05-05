package com.example.user.domain.entities

import com.example.altabib.core.models.DayOfWeek

data class Availability(
    val days: List<DayOfWeek> = emptyList(),
    val hours: List<TimeWindow> = emptyList()
)
