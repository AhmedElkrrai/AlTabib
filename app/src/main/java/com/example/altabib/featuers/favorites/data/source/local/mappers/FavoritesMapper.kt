package com.example.altabib.featuers.favorites.data.source.local.mappers

import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.featuers.favorites.data.source.local.models.FavoriteDoctorEntity
import com.example.altabib.featuers.dashboard.domain.entities.Specialization

fun Doctor.toEntity(): FavoriteDoctorEntity {
    return FavoriteDoctorEntity(
        id = id,
        name = name,
        specialization = specialization.key,
        rating = rating.toDouble(),
        reviews = reviews,
        availability = availability,
    )
}

fun FavoriteDoctorEntity.toDoctor(): Doctor {
    return Doctor(
        id = id,
        name = name,
        specialization = Specialization.fromKey(specialization),
        rating = rating.toFloat(),
        reviews = reviews,
        availability = availability,
    )
}
