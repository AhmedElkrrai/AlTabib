package com.example.doctors.data.source.remote.mappers

import com.example.doctors.data.source.remote.models.DoctorDto
import com.example.doctors.data.source.remote.models.ReviewDto
import com.example.user.domain.entities.Doctor
import com.example.user.domain.entities.Review

fun DoctorDto.toDomain(): Doctor {
    return Doctor(
        id = id,
        name = name,
        avatar = avatar,
        specKey = specialization,
        rating = rating.toFloat(),
        reviews = reviews,
        bio = bio,
        availability = availability,
        inQueue = inQueue,
        price = price,
        premium = premium,
        address = address,
        city = city,
        reviewsList = reviewsList.map { it.toDomain() }
    )
}

fun Doctor.toDto(): DoctorDto {
    return DoctorDto(
        id = id,
        name = name,
        avatar = avatar,
        specialization = specKey,
        rating = rating.toDouble(),
        reviews = reviews,
        bio = bio,
        availability = availability,
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
