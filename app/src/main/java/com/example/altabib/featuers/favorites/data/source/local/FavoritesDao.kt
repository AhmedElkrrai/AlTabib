package com.example.altabib.featuers.favorites.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorite_doctors")
    suspend fun getFavorites(): List<FavoriteDoctorEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_doctors WHERE id = :doctorId)")
    suspend fun isFavorite(doctorId: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(doctor: FavoriteDoctorEntity)

    @Delete
    suspend fun removeFavorite(doctor: FavoriteDoctorEntity)
}
