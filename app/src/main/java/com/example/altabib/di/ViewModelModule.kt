package com.example.altabib.di

import com.example.altabib.featuers.dashboard.presentation.booking.BookingViewModel
import com.example.altabib.featuers.dashboard.presentation.dashboard.DashboardViewModel
import com.example.altabib.featuers.dashboard.presentation.doctor.DoctorDetailsViewModel
import com.example.altabib.featuers.dashboard.presentation.specialization.SpecializationViewModel
import com.example.altabib.featuers.favorites.presentation.FavoritesViewModel
import com.example.altabib.featuers.settings.presentation.SettingsViewModel
import com.example.altabib.featuers.user.presentation.auth.AuthViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get(), get(), get()) }
    viewModel { SettingsViewModel(get(), get(), get()) }
    viewModel { DashboardViewModel(get()) }
    viewModel { FavoritesViewModel(get(), get()) }
    viewModel { SpecializationViewModel(get()) }
    viewModel { BookingViewModel(get(), get(), get(), get(), get()) }
    viewModel { DoctorDetailsViewModel(get(), get(), get()) }
}