package com.example.doctors.data.source.remote.mappers

import com.example.doctors.data.source.remote.models.AvailabilityDto
import com.example.doctors.data.source.remote.models.AvailableHourDto
import com.example.doctors.data.source.remote.models.DoctorDto
import com.example.doctors.data.source.remote.models.ReviewDto
import com.example.doctors.data.source.remote.models.TimeWindowDto
import com.example.user.domain.entities.Availability
import com.example.altabib.core.models.AvailableHour
import com.example.altabib.core.models.DayOfWeek
import com.example.user.domain.entities.Doctor
import com.example.altabib.core.models.Period
import com.example.user.domain.entities.Review
import com.example.user.domain.entities.TimeWindow

fun Doctor.toDto(): DoctorDto {
    return DoctorDto(
        id = id,
        name = name,
        avatar = avatar,
        specialization = specKey,
        rating = rating.toDouble(),
        reviews = reviews,
        contact = contact,
        bio = bio,
        availability = availability.toDto(),
        inQueue = inQueue,
        price = price,
        premium = premium,
        address = address,
        city = city,
        reviewsList = reviewsList.map { it.toDto() }
    )
}

fun DoctorDto.toDomain(): Doctor {
    return Doctor(
        id = id,
        name = name,
        avatar = avatar,
        specKey = specialization,
        rating = rating.toFloat(),
        reviews = reviews,
        bio = bio,
        availability = availability.toDomain(),
        inQueue = inQueue,
        price = price,
        premium = premium,
        address = address,
        contact = contact,
        city = city,
        reviewsList = reviewsList.map { it.toDomain() }
    )
}

fun AvailabilityDto.toDomain(): Availability {
    return if (days.isNotEmpty() && hours.isNotEmpty()) {
        Availability(
            days = days.map { DayOfWeek.valueOf(it.uppercase()) },
            hours = hours.map { it.toDomain() }
        )
    } else Availability()
}

fun TimeWindowDto.toDomain(): TimeWindow {
    return TimeWindow(
        start = start.toDomain(),
        end = end.toDomain()
    )
}

fun AvailableHourDto.toDomain(): AvailableHour {
    return AvailableHour(
        time = time,
        period = Period.valueOf(period.uppercase())
    )
}

fun Availability.toDto(): AvailabilityDto {
    return AvailabilityDto(
        days = days.map { it.name.uppercase() },
        hours = hours.map { it.toDto() })
}

fun TimeWindow.toDto(): TimeWindowDto {
    return TimeWindowDto(
        start = start.toDto(),
        end = end.toDto()
    )
}

fun AvailableHour.toDto(): AvailableHourDto {
    return AvailableHourDto(
        time = time,
        period = period.name.uppercase()
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
