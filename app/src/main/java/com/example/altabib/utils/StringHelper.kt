package com.example.altabib.utils

import android.content.Context
import androidx.annotation.StringRes

fun getLocalizedString(context: Context, @StringRes resId: Int): String {
    val lang: LocaleHelper.Language = LocaleHelper.getCurrentLanguageEnum(context)
    val localizedContext = LocaleHelper.setLocale(context, lang)
    return localizedContext.getString(resId)
}
