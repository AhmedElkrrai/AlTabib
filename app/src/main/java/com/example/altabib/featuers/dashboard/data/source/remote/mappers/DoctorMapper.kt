package com.example.altabib.featuers.dashboard.data.source.remote.mappers

import com.example.altabib.featuers.dashboard.data.source.remote.models.DoctorDto
import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.featuers.dashboard.domain.entities.Specialization

fun DoctorDto.toDomain(): Doctor {
    return Doctor(
        id = id,
        name = name,
        specialization = Specialization.entries.firstOrNull {
            it.key.equals(specialization, ignoreCase = true)
        } ?: Specialization.GENERAL_PRACTICE,
        rating = rating.toFloat(),
        reviews = reviews,
        bio = bio,
        availability = availability,
        inQueue = inQueue,
        price = price,
        premium = premium,
        address = address,
        city = city
    )
}

fun Doctor.toDto(): DoctorDto {
    return DoctorDto(
        id = id,
        name = name,
        specialization = specialization.key,
        rating = rating.toDouble(),
        reviews = reviews,
        bio = bio,
        availability = availability,
        inQueue = inQueue,
        price = price,
        premium = premium,
        address = address,
        city = city
    )
}
