package com.example.altabib.utils

import androidx.compose.runtime.Composable
import com.example.altabib.R
import com.example.altabib.design_system.getLocalizedString
import java.util.Locale

@Composable
fun getRatingText(rating: Float, reviews: Int): String {
    val stars = String.format(Locale.ENGLISH, "%.1f", rating)
    val txt = getLocalizedString(R.string.reviews)
    return "$stars ($reviews $txt)"
}
