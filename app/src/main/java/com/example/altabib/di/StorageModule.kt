package com.example.altabib.di

import android.content.Context
import com.example.user.data.source.local.UserManager
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
    single { androidContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }
    single { Gson() }
    single { UserManager(get()) }
}
