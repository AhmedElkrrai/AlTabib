package com.example.user.domain.entities

import com.example.altabib.core.models.AvailableHour

data class TimeWindow(
    val start: AvailableHour = AvailableHour(),
    val end: AvailableHour = AvailableHour(),
)