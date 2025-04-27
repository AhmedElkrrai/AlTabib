package com.example.doctors.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.doctors.data.source.local.entites.DoctorEntity

@Dao
interface DoctorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDoctors(doctors: List<DoctorEntity>)

    @Query("SELECT * FROM doctors WHERE specialization = :specialization AND city = :city")
    suspend fun getDoctorsBySpecializationAndCity(specialization: String, city: String): List<DoctorEntity>

    @Query("SELECT * FROM doctors WHERE isFavorite = 1")
    suspend fun getFavorites(): List<DoctorEntity>

    @Query("SELECT id FROM doctors WHERE isFavorite = 1")
    suspend fun getFavoriteDoctorIds(): List<String>

    @Query("UPDATE doctors SET isFavorite = :isFav WHERE id = :doctorId")
    suspend fun updateFavoriteStatus(doctorId: String, isFav: Boolean)
}
