package com.example.altabib.featuers.dashboard.presentation.doctor.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun RatingSection(
    rating: Int,
    onRatingSelected: (Int) -> Unit
) {
    Row(horizontalArrangement = Arrangement.Center) {
        repeat(5) { index ->
            val starIndex = index + 1
            IconButton(onClick = { onRatingSelected(starIndex) }) {
                Icon(
                    imageVector = if (starIndex <= rating) Icons.Default.Star else Icons.Default.StarBorder,
                    contentDescription = null,
                    tint = Color.Yellow
                )
            }
        }
    }
}
