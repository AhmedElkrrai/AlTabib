package com.example.altabib.di

import com.example.analytics.presentation.AnalyticsViewModel
import com.example.appointments.presentation.AppointmentsViewModel
import com.example.doctors.presentation.booking.BookingViewModel
import com.example.doctors.presentation.dashboard.DashboardViewModel
import com.example.doctors.presentation.doctor.DoctorDetailsViewModel
import com.example.doctors.presentation.specialization.SpecializationViewModel
import com.example.favorites.presentation.FavoritesViewModel
import com.example.profile.presentation.availability.AvailabilityViewModel
import com.example.profile.presentation.profile.ProfileViewModel
import com.example.settings.presentation.SettingsViewModel
import com.example.user.presentation.auth.AuthViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
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
    viewModel { AnalyticsViewModel(get(), get(), get()) }

    viewModel {
        ProfileViewModel(
            ioContext = Dispatchers.IO,
            defaultContext = Dispatchers.Default,
            transformer = get()
        )
    }
}
