package com.example.altabib.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import com.example.altabib.utils.LocaleHelper.getLayoutDirection

@Composable
fun FormatCompose(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalLayoutDirection provides getLayoutDirection()) {
        content()
    }
}
