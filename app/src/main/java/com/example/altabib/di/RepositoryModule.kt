package com.example.altabib.di

import com.example.altabib.featuers.appointments.data.source.AppointmentRepositoryImpl
import com.example.altabib.featuers.appointments.domain.AppointmentRepository
import com.example.altabib.featuers.dashboard.data.source.DoctorRepositoryImpl
import com.example.altabib.featuers.dashboard.domain.DoctorRepository
import com.example.settings.data.source.PatientRepositoryImpl
import com.example.settings.domain.PatientRepository
import com.example.user.data.source.AuthRepositoryImpl
import com.example.user.domain.AuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
    singleOf(::DoctorRepositoryImpl).bind<DoctorRepository>()
    singleOf(::AppointmentRepositoryImpl).bind<AppointmentRepository>()
    singleOf(::PatientRepositoryImpl).bind<PatientRepository>()
}
