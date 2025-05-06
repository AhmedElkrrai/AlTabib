package com.example.altabib.di

import com.example.appointments.presentation.AppointmentsViewModel
import com.example.doctors.presentation.booking.BookingViewModel
import com.example.doctors.presentation.dashboard.DashboardViewModel
import com.example.doctors.presentation.doctor.DoctorDetailsViewModel
import com.example.doctors.presentation.specialization.SpecializationViewModel
import com.example.favorites.presentation.FavoritesViewModel
import com.example.profile.presentation.availability.AvailabilityViewModel
import com.example.profile.presentation.profile.ProfileEventHandler
import com.example.profile.presentation.profile.ProfileReducer
import com.example.profile.presentation.profile.ProfileViewModel
import com.example.settings.presentation.SettingsViewModel
import com.example.user.presentation.auth.AuthViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get(), get(), get()) }
    viewModel { SettingsViewModel(get(), get(), get(), get()) }
    viewModel { DashboardViewModel(get()) }
    viewModel { FavoritesViewModel(get(), get()) }
    viewModel { SpecializationViewModel(get()) }
    viewModel { BookingViewModel(get(), get(), get(), get(), get()) }
    viewModel { DoctorDetailsViewModel(get(), get(), get(), get()) }
    viewModel { AppointmentsViewModel(get(), get(), get()) }
    viewModel { AvailabilityViewModel(get(), get(), get()) }

    single { ProfileReducer() }

    factory {
        ProfileEventHandler(
            getUserUseCase = get(),
            getDoctorUseCase = get(),
            updateDoctorUseCase = get(),
            logoutUseCase = get(),
            updateAvatarUseCase = get(),
            byteArrayConverter = get(),
            imageStorage = get()
        )
    }

    viewModel {
        ProfileViewModel(
            reducer = get(),
            eventHandler = get()
        )
    }
}