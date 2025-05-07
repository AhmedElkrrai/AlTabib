package com.example.analytics.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.altabib.design.R
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.design_system.theme.DarkCharcoal
import com.example.altabib.design_system.theme.Primary
import com.example.altabib.design_system.theme.Yellow
import java.util.Locale

@Composable
fun AnalyticsHeader(
    reviews: Int,
    isPremium: Boolean,
    animatedRating: State<Float>,
    views: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Primary, RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = getLocalizedString(R.string.your_analytics),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = getLocalizedString(R.string.rating),
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                val stars = String.format(Locale.ENGLISH, "%.1f", animatedRating.value)
                Text(
                    text = "$stars ⭐",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = getLocalizedString(R.string.reviews_c),
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = reviews.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = getLocalizedString(R.string.visits),
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = views.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }

            if (isPremium) {
                Box(
                    modifier = Modifier
                        .background(Yellow, RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = getLocalizedString(R.string.premium),
                        color = DarkCharcoal,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
