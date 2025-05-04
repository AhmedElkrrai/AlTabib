package com.example.doctors.data.source.local.mappers

import com.example.doctors.data.source.local.entites.DoctorEntity
import com.example.user.domain.entities.Availability
import com.example.user.domain.entities.Doctor

fun Doctor.toEntity(
    isFavorite: Boolean
): DoctorEntity = DoctorEntity(
    id = id,
    name = name,
    specialization = specKey,
    city = city,
    rating = rating.toDouble(),
    reviews = reviews,
    availability = Availability(),
    inQueue = inQueue,
    price = price,
    contact = contact,
    isFavorite = isFavorite
)

fun DoctorEntity.toDomain(): Doctor =
    Doctor(
        id = id,
        name = name,
        specKey = specialization,
        rating = rating.toFloat(),
        reviews = reviews,
        availability = availability,
        inQueue = inQueue,
        price = price,
        city = city,
        bio = "",
        avatar = "",
        premium = false,
        address = "",
        reviewsList = listOf(),
        contact = contact
    )
