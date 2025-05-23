package com.example.altabib.di

import com.example.analytics.data.AnalyticsRepositoryImpl
import com.example.analytics.domain.AnalyticsRepository
import com.example.appointments.data.source.AppointmentRepositoryImpl
import com.example.appointments.domain.AppointmentRepository
import com.example.doctors.data.source.DoctorRepositoryImpl
import com.example.doctors.domain.DoctorRepository
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
    singleOf(::AnalyticsRepositoryImpl).bind<AnalyticsRepository>()
}
