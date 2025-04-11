package com.example.altabib.featuers.dashboard.presentation.doctor.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.altabib.featuers.dashboard.presentation.doctor.DoctorDetailsAction

@Composable
fun ReviewSection(
    modifier: Modifier = Modifier,
    onAction: (DoctorDetailsAction) -> Unit,
) {
    var userRating by remember { mutableStateOf<Int?>(null) }

    Text(
        text = "Rate this doctor",
        style = MaterialTheme.typography.titleMedium
    )

    Spacer(modifier = Modifier.height(8.dp))

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
                    .clickable(enabled = userRating == null) {
                        userRating = star
                        onAction(DoctorDetailsAction.OnSubmitRating(star))
                    }
            )
        }
    }
}
