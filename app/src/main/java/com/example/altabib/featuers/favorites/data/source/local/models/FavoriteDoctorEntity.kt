package com.example.altabib.featuers.favorites.data.source.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_doctors")
data class FavoriteDoctorEntity(
    @PrimaryKey val id: String,
    val name: String,
    val specialization: String,
    val rating: Double,
    val reviews: Int,
    val availability: String,
)
