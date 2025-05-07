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
    val result = views
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
    // TODO: return result instead of dummyViews
    return dummyViews
}

fun viewsPerMonth(viewData: List<ViewData>): Int {
    var totalViews = 0
    viewData.forEach { view ->
        totalViews += view.viewCount
    }
    return totalViews
}

val dummyViews: List<ViewData> =
    listOf(
        ViewData(1, 80),
        ViewData(2, 95),
        ViewData(3, 30),
        ViewData(4, 65),
        ViewData(5, 50),
        ViewData(6, 45),
        ViewData(7, 40),
        ViewData(8, 45),
        ViewData(9, 52),
        ViewData(10, 55),
        ViewData(11, 107),
        ViewData(12, 95),
        ViewData(13, 70),
        ViewData(14, 75),
        ViewData(15, 80),
        ViewData(16, 25),
        ViewData(17, 90),
        ViewData(18, 95),
        ViewData(19, 33),
        ViewData(20, 66),
        ViewData(21, 44),
        ViewData(22, 55),
        ViewData(23, 66),
        ViewData(24, 77),
        ViewData(25, 88),
        ViewData(26, 99),
        ViewData(27, 10),
        ViewData(28, 11),
        ViewData(29, 12),
        ViewData(30, 13),
    )

