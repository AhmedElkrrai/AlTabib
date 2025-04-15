package com.example.altabib.di

import com.example.altabib.featuers.dashboard.domain.usecases.GetDoctorByIdUseCase
import com.example.altabib.featuers.dashboard.domain.usecases.GetDoctorsBySpecializationUseCase
import com.example.altabib.featuers.appointments.domain.usecases.SaveAppointmentUseCase
import com.example.altabib.featuers.dashboard.domain.usecases.SearchDoctorsUseCase
import com.example.altabib.featuers.dashboard.domain.usecases.UpdateDoctorUseCase
import com.example.altabib.featuers.favorites.domain.usecases.AddFavoriteUseCase
import com.example.altabib.featuers.favorites.domain.usecases.GetFavoritesUseCase
import com.example.altabib.featuers.favorites.domain.usecases.IsFavoriteUseCase
import com.example.altabib.featuers.favorites.domain.usecases.RemoveFavoriteUseCase
import com.example.altabib.featuers.settings.domain.usecases.GetPatientUseCase
import com.example.altabib.featuers.settings.domain.usecases.UpdatePatientUseCase
import com.example.altabib.featuers.user.domain.usecases.CacheUserUseCase
import com.example.altabib.featuers.user.domain.usecases.GetUserUseCase
import com.example.altabib.featuers.user.domain.usecases.GoogleSignInUseCase
import com.example.altabib.featuers.user.domain.usecases.LogoutUseCase
import com.example.altabib.featuers.user.domain.usecases.RegisterUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::SearchDoctorsUseCase)
    singleOf(::GetDoctorsBySpecializationUseCase)
    singleOf(::SaveAppointmentUseCase)
    singleOf(::GetDoctorByIdUseCase)
    singleOf(::GoogleSignInUseCase)
    singleOf(::RegisterUseCase)
    singleOf(::LogoutUseCase)
    singleOf(::UpdateDoctorUseCase)
    singleOf(::UpdatePatientUseCase)
    singleOf(::GetPatientUseCase)
    singleOf(::GetUserUseCase)
    singleOf(::CacheUserUseCase)
    singleOf(::IsFavoriteUseCase)
    singleOf(::GetFavoritesUseCase)
    singleOf(::AddFavoriteUseCase)
    singleOf(::RemoveFavoriteUseCase)
}