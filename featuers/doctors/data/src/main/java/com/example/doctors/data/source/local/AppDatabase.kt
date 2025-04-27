package com.example.doctors.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.doctors.data.source.local.entites.DoctorEntity

@Database(
    entities = [DoctorEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun doctorsDao(): DoctorDao
}
