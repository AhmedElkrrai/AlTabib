package com.example.altabib.di

import android.content.Context
import com.example.altabib.featuers.dashboard.presentation.dashboard.DashboardViewModel
import com.example.altabib.featuers.settings.presentation.SettingsViewModel
import com.example.altabib.featuers.user.data.source.AuthRepositoryImpl
import com.example.altabib.featuers.user.data.source.local.UserManager
import com.example.altabib.featuers.user.data.source.remote.AuthenticationService
import com.example.altabib.featuers.user.domain.AuthRepository
import com.example.altabib.featuers.user.domain.usecases.GoogleSignInUseCase
import com.example.altabib.featuers.user.domain.usecases.RegisterUseCase
import com.example.altabib.featuers.user.domain.usecases.LogoutUseCase
import com.example.altabib.featuers.user.domain.usecases.GetUserUseCase
import com.example.altabib.featuers.user.domain.usecases.SaveUserUseCase
import com.example.altabib.featuers.dashboard.domain.usecases.SearchDoctorsUseCase
import com.example.altabib.featuers.dashboard.domain.usecases.GetDoctorsBySpecializationUseCase
import com.example.altabib.featuers.dashboard.domain.usecases.GetDoctorByIdUseCase
import com.example.altabib.featuers.dashboard.presentation.doctor.DoctorDetailsViewModel
import com.example.altabib.featuers.dashboard.presentation.specialization.SpecializationViewModel
import com.example.altabib.featuers.user.presentation.auth.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Koin module for dependency injection in the application.
 *
 * This module defines how various dependencies are created and provided throughout the application.
 * It uses the Koin dependency injection framework to manage the creation and lifecycle of objects.
 */
val appModule = module {
    single { Gson() }
    single {
        androidContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }
    single { UserManager(get()) }
    single { provideGoogleSignInClient(androidContext()) }
    single { FirebaseAuth.getInstance() }
    singleOf(::AuthenticationService)
    single { FirebaseFirestore.getInstance() }
    singleOf(::SearchDoctorsUseCase)
    singleOf(::GetDoctorsBySpecializationUseCase)
    singleOf(::GetDoctorByIdUseCase)
    singleOf(::GoogleSignInUseCase)
    singleOf(::RegisterUseCase)
    singleOf(::LogoutUseCase)
    singleOf(::GetUserUseCase)
    singleOf(::SaveUserUseCase)
    viewModel { AuthViewModel(get(), get(), get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { DashboardViewModel(get()) }
    viewModel { SpecializationViewModel(get()) }
    viewModel { DoctorDetailsViewModel(get()) }
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}
