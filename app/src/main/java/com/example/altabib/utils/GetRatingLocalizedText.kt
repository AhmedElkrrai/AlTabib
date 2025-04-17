package com.example.altabib.utils

import androidx.compose.runtime.Composable
import com.example.altabib.R

@Composable
fun getRatingText(rating: Float, reviews: Int): String {
    return "${"%.1f".format(rating)} (${reviews} " +
            getLocalizedString(R.string.reviews) + ")"
}
