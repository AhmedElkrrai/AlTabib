package com.example.altabib.di

import com.example.user.data.source.remote.AuthenticationService
import com.example.user.data.source.remote.GoogleSignInHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { GoogleSignInHelper(androidContext()).getClient() }
    singleOf(::AuthenticationService)
}
