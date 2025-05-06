package com.example.analytics.presentation.util

import com.example.altabib.core.getThisMonth
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class ViewData(val day: Int, val viewCount: Int)

fun processViewData(
    views: Map<String, List<String>>,
    month: String = getThisMonth()
): List<ViewData> {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return views
        .mapNotNull { (date, patientIds) ->
            try {
                val localDate = LocalDate.parse(date, formatter)
                if (localDate.monthValue == month.toInt()) {
                    ViewData(localDate.dayOfMonth, patientIds.size)
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
        .sortedBy { it.day }
}
