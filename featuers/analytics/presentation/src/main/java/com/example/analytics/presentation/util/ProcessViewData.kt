package com.example.analytics.presentation.util

import com.example.altabib.core.getThisMonth
import com.example.altabib.core.models.ViewData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun processViewData(
    views: Map<String, List<String>>,
    month: String = getThisMonth()
): List<ViewData> {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    views
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

    return dummyViews
}

val dummyViews: List<ViewData> =
    listOf(
        ViewData(1, 10),
        ViewData(2, 15),
        ViewData(3, 20),
        ViewData(4, 25),
        ViewData(5, 30),
        ViewData(6, 35),
        ViewData(7, 40),
        ViewData(8, 45),
        ViewData(9, 50),
        ViewData(10, 55),
        ViewData(11, 107),
        ViewData(12, 15),
        ViewData(13, 70),
        ViewData(14, 75),
        ViewData(15, 80),
        ViewData(16, 25),
        ViewData(17, 90),
        ViewData(18, 95),
        ViewData(19, 33),
        ViewData(20, 66),
    )

