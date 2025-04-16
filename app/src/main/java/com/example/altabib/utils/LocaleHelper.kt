package com.example.altabib.utils

import android.content.Context
import android.content.res.Configuration
import android.preference.PreferenceManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import java.util.Locale

object LocaleHelper {

    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"

    enum class Language(val code: String) {
        ENGLISH("en"),
        ARABIC("ar");

        companion object {
            fun fromCode(code: String): Language {
                return entries.find { it.code == code } ?: ARABIC
            }
        }
    }

    fun setLocale(context: Context, language: Language): Context {
        persist(context, language)
        return updateResources(context, language)
    }

    private fun getCurrentLanguage(context: Context): String {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(SELECTED_LANGUAGE, Language.ENGLISH.code)
            ?: Language.ENGLISH.code
    }

    fun getCurrentLanguageEnum(context: Context): Language {
        return Language.fromCode(getCurrentLanguage(context))
    }

    private fun persist(context: Context, language: Language) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        preferences.edit().putString(SELECTED_LANGUAGE, language.code).apply()
    }

    private fun updateResources(context: Context, language: Language): Context {
        val locale = Locale(language.code)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        config.setLayoutDirection(locale)

        return context.createConfigurationContext(config)
    }

    @Composable
    fun isArabic(): Boolean {
        val context = LocalContext.current
        return getCurrentLanguageEnum(context) == Language.ARABIC
    }

    @Composable
    fun getCurrentLanguage(): Language {
        val context = LocalContext.current
        return getCurrentLanguageEnum(context)
    }

    @Composable
    fun getLayoutDirection(): LayoutDirection {
        return when (getCurrentLanguageEnum(LocalContext.current)) {
            LocaleHelper.Language.ARABIC -> LayoutDirection.Rtl
            LocaleHelper.Language.ENGLISH -> LayoutDirection.Ltr
        }
    }
}
