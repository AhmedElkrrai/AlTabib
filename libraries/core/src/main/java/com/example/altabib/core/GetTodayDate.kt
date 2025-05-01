package com.example.altabib.core

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun getTodayDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDate.now().format(formatter)
}
