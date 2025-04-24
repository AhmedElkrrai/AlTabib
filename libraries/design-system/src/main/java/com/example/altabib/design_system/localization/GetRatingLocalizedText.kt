package com.example.altabib.design_system.localization

import androidx.compose.runtime.Composable
import com.example.altabib.design.R
import java.util.Locale

@Composable
fun getRatingText(rating: Float, reviews: Int): String {
    val stars = String.format(Locale.ENGLISH, "%.1f", rating)
    val txt = getLocalizedString(R.string.reviews)
    return "$stars ($reviews $txt)"
}
