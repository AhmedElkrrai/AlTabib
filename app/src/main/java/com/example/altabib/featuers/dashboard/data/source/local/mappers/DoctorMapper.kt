package com.example.altabib.featuers.dashboard.data.source.local.mappers

import com.example.altabib.featuers.dashboard.data.source.local.entites.DoctorEntity
import com.example.altabib.featuers.user.domain.entities.Doctor
import com.example.altabib.featuers.dashboard.presentation.specialization.models.Specialization

fun Doctor.toEntity(
    isFavorite: Boolean
): DoctorEntity = DoctorEntity(
    id,
    name,
    specKey,
    city,
    rating.toDouble(),
    reviews,
    availability,
    inQueue,
    price,
    isFavorite
)

fun DoctorEntity.toDomain(): Doctor = Doctor(
    id = id,
    name = name,
    specKey = Specialization.DERMATOLOGY.key,
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
    reviewsList = listOf()
)
