package com.example.doctors.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.doctors.data.source.local.entites.DoctorEntity
import com.example.doctors.data.source.local.util.DoctorTypeConverters

@Database(
    entities = [DoctorEntity::class],
    version = 1
)
@TypeConverters(DoctorTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun doctorsDao(): DoctorDao
}
