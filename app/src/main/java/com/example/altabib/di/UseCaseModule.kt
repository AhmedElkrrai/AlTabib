package com.example.altabib.di

import com.example.analytics.domain.usecases.GetProfileViewsUseCase
import com.example.analytics.domain.usecases.UpdateProfileViewsUseCase
import com.example.appointments.domain.usecases.DismissAppointmentUseCase
import com.example.appointments.domain.usecases.GetAppointmentsUseCase
import com.example.appointments.domain.usecases.SaveAppointmentUseCase
import com.example.doctors.domain.usecases.GetDoctorUseCase
import com.example.doctors.domain.usecases.GetDoctorsBySpecializationUseCase
import com.example.doctors.domain.usecases.SearchDoctorsUseCase
import com.example.doctors.domain.usecases.UpdateAvailabilityUseCase
import com.example.doctors.domain.usecases.UpdateAvatarUseCase
import com.example.doctors.domain.usecases.UpdateDoctorUseCase
import com.example.favorites.domain.usecases.AddFavoriteUseCase
import com.example.favorites.domain.usecases.GetFavoritesUseCase
import com.example.favorites.domain.usecases.IsFavoriteUseCase
import com.example.favorites.domain.usecases.RemoveFavoriteUseCase
import com.example.settings.domain.usecases.GetPatientUseCase
import com.example.settings.domain.usecases.UpdatePatientUseCase
import com.example.user.domain.usecases.CacheUserUseCase
import com.example.user.domain.usecases.GetUserUseCase
import com.example.user.domain.usecases.GoogleSignInUseCase
import com.example.user.domain.usecases.LogoutUseCase
import com.example.user.domain.usecases.RegisterUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::SearchDoctorsUseCase)
    singleOf(::GetDoctorsBySpecializationUseCase)
    singleOf(::SaveAppointmentUseCase)
    singleOf(::GetAppointmentsUseCase)
    singleOf(::DismissAppointmentUseCase)
    singleOf(::GetDoctorUseCase)
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
    singleOf(::GetProfileViewsUseCase)
    singleOf(::UpdateProfileViewsUseCase)
    singleOf(::UpdateAvatarUseCase)
    singleOf(::UpdateAvailabilityUseCase)
}