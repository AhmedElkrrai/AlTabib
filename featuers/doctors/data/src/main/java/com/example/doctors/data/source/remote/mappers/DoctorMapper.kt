package com.example.doctors.data.source.remote.mappers

import android.util.Log
import com.example.doctors.data.source.remote.models.DoctorDto
import com.example.doctors.data.source.remote.models.ReviewDto
import com.example.doctors.data.source.remote.util.GsonProvider.gson
import com.example.user.domain.entities.Availability
import com.example.user.domain.entities.Doctor
import com.example.user.domain.entities.Review

fun DoctorDto.toDomain(): Doctor {
    val availabilityObj = try {
        if (availability.isNotBlank()) {
            gson.fromJson(availability, Availability::class.java)
        } else null
    } catch (e: Exception) {
        Log.e("DoctorDto", "Error parsing availability", e)
        null
    }

    return Doctor(
        id = id,
        name = name,
        avatar = avatar,
        specKey = specialization,
        rating = rating.toFloat(),
        reviews = reviews,
        bio = bio,
        availability = availabilityObj,
        inQueue = inQueue,
        price = price,
        premium = premium,
        address = address,
        contact = contact,
        city = city,
        reviewsList = reviewsList.map { it.toDomain() }
    )
}

fun Doctor.toDto(): DoctorDto {
    val availabilityJson = gson.toJson(availability)
    return DoctorDto(
        id = id,
        name = name,
        avatar = avatar,
        specialization = specKey,
        rating = rating.toDouble(),
        reviews = reviews,
        contact = contact,
        bio = bio,
        availability = availabilityJson,
        inQueue = inQueue,
        price = price,
        premium = premium,
        address = address,
        city = city,
        reviewsList = reviewsList.map { it.toDto() }
    )
}

fun ReviewDto.toDomain(): Review {
    return Review(
        id = id,
        userName = userName,
        text = text,
        rating = rating,
        createdAt = createdAt
    )
}

fun Review.toDto(): ReviewDto {
    return ReviewDto(
        id = id,
        userName = userName,
        text = text,
        rating = rating,
        createdAt = createdAt
    )
}
