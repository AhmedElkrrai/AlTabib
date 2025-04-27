package com.example.altabib.utils

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.altabib.home.presentation.MainActivity
import com.example.altabib.design_system.localization.LocaleHelper

@Composable
fun RestartApp(
    language: LocaleHelper.Language
) {
    val updatedContext = LocaleHelper.setLocale(LocalContext.current, language)
    val intent = Intent(updatedContext, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    updatedContext.startActivity(intent)
}
