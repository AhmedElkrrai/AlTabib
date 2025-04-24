package com.example.altabib.design_system

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun getLocalizedString(@StringRes resId: Int): String {
    val context = LocalContext.current
    val lang: LocaleHelper.Language = LocaleHelper.getCurrentLanguageEnum(context)
    val localizedContext = LocaleHelper.setLocale(context, lang)
    return localizedContext.getString(resId)
}
