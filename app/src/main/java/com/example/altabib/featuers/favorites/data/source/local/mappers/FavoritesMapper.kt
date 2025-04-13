package com.example.altabib.featuers.favorites.data.source.local.mappers

import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.featuers.favorites.data.source.local.FavoriteDoctorEntity
import com.example.altabib.featuers.dashboard.domain.entities.Specialization

fun Doctor.toEntity(): FavoriteDoctorEntity {
    return FavoriteDoctorEntity(
        id = id,
        name = name,
        specialization = specialization.key,
        city = city,
        rating = rating.toDouble(),
        reviews = reviews
    )
}

fun FavoriteDoctorEntity.toDoctor(): Doctor {
    return Doctor(
        id = id,
        name = name,
        specialization = Specialization.fromKey(specialization),
        city = city,
        rating = rating.toFloat(),
        reviews = reviews
    )
}
