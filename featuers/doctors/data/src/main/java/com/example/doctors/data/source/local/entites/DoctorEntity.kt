package com.example.doctors.data.source.local.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.user.domain.entities.Availability

@Entity(tableName = "doctors")
data class DoctorEntity(
    @PrimaryKey val id: String,
    val name: String,
    val specialization: String,
    val city: String,
    val rating: Double,
    val reviews: Int,
    val availability: Availability,
    val inQueue: Int,
    val price: Int,
    val contact: String,
    val isFavorite: Boolean,
)
