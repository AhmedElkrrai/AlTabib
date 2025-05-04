package com.example.doctors.presentation.doctor.util

import androidx.compose.runtime.Composable
import com.example.altabib.design.R
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.user.domain.entities.AvailableHour
import com.example.user.domain.entities.DayOfWeek
import com.example.user.domain.entities.Period

@Composable
fun formatAvailableHour(hour: AvailableHour): String {
    val time = if (hour.time in 1..12) hour.time else 1 // fallback to 1 if invalid
    return "$time ${hour.period.displayName()}"
}

@Composable
fun DayOfWeek.displayName(): String {
    return when (this) {
        DayOfWeek.MONDAY -> getLocalizedString(R.string.monday)
        DayOfWeek.TUESDAY -> getLocalizedString(R.string.tuesday)
        DayOfWeek.WEDNESDAY -> getLocalizedString(R.string.wednesday)
        DayOfWeek.THURSDAY -> getLocalizedString(R.string.thursday)
        DayOfWeek.FRIDAY -> getLocalizedString(R.string.friday)
        DayOfWeek.SATURDAY -> getLocalizedString(R.string.saturday)
        DayOfWeek.SUNDAY -> getLocalizedString(R.string.sunday)
    }
}

@Composable
fun Period.displayName(): String {
    return when (this) {
        Period.AM -> getLocalizedString(R.string.am)
        Period.PM -> getLocalizedString(R.string.pm)
    }
}