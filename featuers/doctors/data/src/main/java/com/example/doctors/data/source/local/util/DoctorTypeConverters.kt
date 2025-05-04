package com.example.doctors.data.source.local.util

import androidx.room.TypeConverter
import com.example.doctors.data.source.remote.util.GsonProvider.gson
import com.example.user.domain.entities.Availability
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object DoctorTypeConverters {
    @TypeConverter
    fun fromAvailability(value: Availability?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toAvailability(value: String?): Availability? {
        return value?.let {
            val type: Type = object : TypeToken<Availability>() {}.type
            gson.fromJson(it, type)
        }
    }
}
