package com.example.doctors.data.source.remote.util

import com.example.user.domain.entities.DayOfWeek
import com.example.user.domain.entities.Period
import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonProvider {
    val gson: Gson = GsonBuilder()
        .registerTypeAdapter(DayOfWeek::class.java, EnumTypeAdapter(DayOfWeek::class.java))
        .registerTypeAdapter(Period::class.java, EnumTypeAdapter(Period::class.java))
        .create()
}
