package com.example.altabib.di

import com.example.appointments.presentation.AppointmentsViewModel
import com.example.doctors.presentation.booking.BookingViewModel
import com.example.doctors.presentation.dashboard.DashboardViewModel
import com.example.doctors.presentation.doctor.DoctorDetailsViewModel
import com.example.doctors.presentation.specialization.SpecializationViewModel
import com.example.favorites.presentation.FavoritesViewModel
import com.example.profile.presentation.ProfileViewModel
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
    viewModel { ProfileViewModel(get(), get(), get(), get(), get(), get(), get()) }
}