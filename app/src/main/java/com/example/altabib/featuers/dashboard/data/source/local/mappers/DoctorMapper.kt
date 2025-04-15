package com.example.altabib.featuers.dashboard.data.source.local.mappers

import com.example.altabib.featuers.dashboard.data.source.local.entites.DoctorEntity
import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.featuers.dashboard.domain.entities.Specialization

fun Doctor.toEntity(
    isFavorite: Boolean
): DoctorEntity = DoctorEntity(
    id,
    name,
    specialization.key,
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
    specialization = Specialization.DERMATOLOGY,
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
