package com.example.altabib.featuers.dashboard.data.source.local.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctors")
data class DoctorEntity(
    @PrimaryKey val id: String,
    val name: String,
    val specialization: String,
    val city: String,
    val rating: Double,
    val reviews: Int,
    val availability: String,
    val inQueue: Int,
    val price: Int,
    val isFavorite: Boolean
)
