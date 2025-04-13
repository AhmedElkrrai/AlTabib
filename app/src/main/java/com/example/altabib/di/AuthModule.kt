package com.example.altabib.di

import com.example.altabib.featuers.user.data.source.remote.AuthenticationService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { provideGoogleSignInClient(androidContext()) }
    singleOf(::AuthenticationService)
}
