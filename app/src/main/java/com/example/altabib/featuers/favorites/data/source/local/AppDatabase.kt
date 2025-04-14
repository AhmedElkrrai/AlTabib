package com.example.altabib.featuers.favorites.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.altabib.featuers.favorites.data.source.local.models.FavoriteDoctorEntity

@Database(
    entities = [FavoriteDoctorEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}
