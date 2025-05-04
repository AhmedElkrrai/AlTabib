package com.example.altabib.di

import android.content.Context
import com.example.altabib.core.LocalImageStorage
import com.example.altabib.design_system.utils.ByteArrayConverter
import com.example.altabib.design_system.utils.ByteArrayConverterImpl
import com.example.altabib.design_system.utils.LocalImageStorageImpl
import com.example.user.data.source.local.UserManager
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
    single { androidContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE) }
    single { Gson() }
    single { UserManager(get()) }
    single<ByteArrayConverter> { ByteArrayConverterImpl(get()) }
    single<LocalImageStorage> { LocalImageStorageImpl(get(), get()) }
}
