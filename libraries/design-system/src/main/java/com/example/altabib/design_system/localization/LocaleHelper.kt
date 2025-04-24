package com.example.altabib.design_system.localization

import android.content.Context
import android.content.res.Configuration
import android.preference.PreferenceManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import java.util.Locale

object LocaleHelper {

    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"

    private val defaultLang = Language.ARABIC

    enum class Language(val code: String) {
        ENGLISH("en"),
        ARABIC("ar");

        companion object {
            fun fromCode(code: String): Language {
                return entries.find { it.code == code } ?: defaultLang
            }
        }
    }

    fun setLocale(context: Context, language: Language): Context {
        persist(context, language)
        return updateResources(context, language)
    }

    private fun getCurrentLanguage(context: Context): String {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(SELECTED_LANGUAGE, defaultLang.code)
            ?: defaultLang.code
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
        return getCurrentLanguage() == Language.ARABIC
    }

    @Composable
    fun getCurrentLanguage(): Language {
        val context = LocalContext.current
        return getCurrentLanguageEnum(context)
    }

    @Composable
    fun getLayoutDirection(): LayoutDirection {
        return when (getCurrentLanguageEnum(LocalContext.current)) {
            Language.ARABIC -> LayoutDirection.Rtl
            Language.ENGLISH -> LayoutDirection.Ltr
        }
    }
}
