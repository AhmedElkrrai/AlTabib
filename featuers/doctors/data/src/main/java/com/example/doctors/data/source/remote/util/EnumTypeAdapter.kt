package com.example.doctors.data.source.remote.util

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class EnumTypeAdapter<T : Enum<T>>(private val enumClass: Class<T>) :
    JsonSerializer<T>,
    JsonDeserializer<T> {
    override fun serialize(
        src: T?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src?.name)
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): T {
        return java.lang.Enum.valueOf(enumClass, json!!.asString)
    }
}
