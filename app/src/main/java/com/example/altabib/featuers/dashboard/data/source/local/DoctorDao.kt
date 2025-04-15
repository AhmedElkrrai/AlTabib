package com.example.altabib.featuers.dashboard.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.altabib.featuers.dashboard.data.source.local.entites.DoctorEntity

@Dao
interface DoctorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDoctors(doctors: List<DoctorEntity>)

    @Query("SELECT * FROM doctors WHERE specialization = :specialization AND city = :city")
    suspend fun getDoctorsBySpecializationAndCity(specialization: String, city: String): List<DoctorEntity>
}
