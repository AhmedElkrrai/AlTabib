package com.example.altabib.di

import androidx.room.Room
import com.example.altabib.featuers.dashboard.data.source.local.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single { get<AppDatabase>().doctorsDao() }
}
