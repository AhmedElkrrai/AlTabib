package com.example.altabib

import android.app.Application
import android.content.Context
import com.example.altabib.di.authModule
import com.example.altabib.di.databaseModule
import com.example.altabib.di.networkModule
import com.example.altabib.di.repositoryModule
import com.example.altabib.di.storageModule
import com.example.altabib.di.useCaseModule
import com.example.altabib.di.viewModelModule
import com.example.altabib.design_system.localization.LocaleHelper
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        startKoin {
            androidContext(this@App)
            modules(
                databaseModule,
                networkModule,
                storageModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
                authModule
            )
        }
    }

    override fun attachBaseContext(newBase: Context) {
        val updatedContext =
            LocaleHelper.setLocale(newBase, LocaleHelper.getCurrentLanguageEnum(newBase))
        super.attachBaseContext(updatedContext)
    }
}
