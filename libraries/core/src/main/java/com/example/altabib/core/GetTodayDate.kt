package com.example.altabib.core

import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import kotlin.text.padStart

fun getTodayDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDate.now().format(formatter)
}

fun getThisMonth(): String {
    val month: Month = LocalDate.now().month
    return month.value.toString().padStart(2, '0')
}
