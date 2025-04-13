package com.example.altabib.featuers.favorites.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_doctors")
data class FavoriteDoctorEntity(
    @PrimaryKey val id: String,
    val name: String,
    val specialization: String,
    val city: String,
    val rating: Double,
    val reviews: Int
)
