package com.example.altabib.featuers.dashboard.presentation.doctor.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.altabib.design.R
import com.example.altabib.design_system.localization.getLocalizedString

@Composable
fun RatingSection(
    currentRating: Int,
    modifier: Modifier = Modifier,
    onRatingSelected: (Int) -> Unit,
) {
    var userRating by remember { mutableIntStateOf(currentRating) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(getLocalizedString(R.string.rate_doctor))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            (1..5).forEach { star ->
                val filled = (userRating ?: 0) >= star
                val scale by animateFloatAsState(
                    targetValue = if (filled) 1.3f else 1f,
                    animationSpec = tween(200),
                    label = "star_scale_$star"
                )
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star $star",
                    tint = if (filled) Color.Yellow else Color.Gray,
                    modifier = modifier
                        .size(32.dp)
                        .graphicsLayer(scaleX = scale, scaleY = scale)
                        .clickable(enabled = userRating == 0) {
                            userRating = star
                            onRatingSelected(star)
                        }
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}
