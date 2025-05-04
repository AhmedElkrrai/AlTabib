package com.example.doctors.presentation.doctor.util

import androidx.compose.runtime.Composable
import com.example.altabib.design.R
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.user.domain.entities.Availability
import com.example.user.domain.entities.AvailableHour
import com.example.user.domain.entities.DayOfWeek
import com.example.user.domain.entities.Period

@Composable
fun Availability?.format(): String {
    if (this == null) return getLocalizedString(R.string.not_available)

    val daysText = buildString {
        this@format.days.forEachIndexed { index, day ->
            append(day.displayName())
            if (index < this@format.days.size - 1) append(" - ")
        }
    }

    val hoursText = buildString {
        this@format.hours.forEachIndexed { index, window ->
            append("${formatAvailableHour(window.start)} - ${formatAvailableHour(window.end)}")
            if (index < this@format.hours.size - 1) append("\n")
        }
    }

    return "$daysText\n$hoursText"
}

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