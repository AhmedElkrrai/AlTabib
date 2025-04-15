package com.example.altabib.di

import com.example.altabib.featuers.appointments.data.source.AppointmentRepositoryImpl
import com.example.altabib.featuers.appointments.domain.AppointmentRepository
import com.example.altabib.featuers.dashboard.data.source.DoctorRepositoryImpl
import com.example.altabib.featuers.dashboard.domain.DoctorRepository
import com.example.altabib.featuers.favorites.data.source.FavoritesRepositoryImpl
import com.example.altabib.featuers.favorites.domain.FavoritesRepository
import com.example.altabib.featuers.settings.data.source.PatientRepositoryImpl
import com.example.altabib.featuers.settings.domain.PatientRepository
import com.example.altabib.featuers.user.data.source.AuthRepositoryImpl
import com.example.altabib.featuers.user.domain.AuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
    singleOf(::DoctorRepositoryImpl).bind<DoctorRepository>()
    singleOf(::AppointmentRepositoryImpl).bind<AppointmentRepository>()
    singleOf(::PatientRepositoryImpl).bind<PatientRepository>()
    singleOf(::FavoritesRepositoryImpl).bind<FavoritesRepository>()
}
