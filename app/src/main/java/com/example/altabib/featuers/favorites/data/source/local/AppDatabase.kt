package com.example.altabib.featuers.favorites.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.altabib.featuers.dashboard.data.source.local.DoctorDao
import com.example.altabib.featuers.dashboard.data.source.local.entites.DoctorEntity
import com.example.altabib.featuers.favorites.data.source.local.models.FavoriteDoctorEntity

@Database(
    entities = [FavoriteDoctorEntity::class, DoctorEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
    abstract fun doctorsDao(): DoctorDao
}
