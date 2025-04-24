package com.example.altabib.featuers.dashboard.presentation.doctor.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.altabib.R
import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.design_system.theme.Green
import com.example.altabib.design_system.theme.Primary
import com.example.altabib.design_system.utils.FormatCompose
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.design_system.localization.getRatingText

@Composable
fun DoctorCard(
    doctor: Doctor,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FormatCompose {
        Card(
            modifier = modifier
                .clickable { onClick() },
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Primary)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = " ${doctor.price} " + getLocalizedString(R.string.currency),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    color = Green,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopEnd)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Avatar
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = doctor.name,
                        modifier = Modifier.size(84.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Name
                    Text(
                        text = doctor.name,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Specialization
                    Text(
                        text = doctor.specialization.getDisplayName(),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Rating
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color.Yellow,
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = getRatingText(doctor.rating, doctor.reviews),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // In Queue
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Green)) {
                                append("${doctor.inQueue}")
                            }
                            withStyle(style = SpanStyle(color = Color.White)) {
                                append(" " + getLocalizedString(R.string.in_queue))
                            }
                        },
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Availability
                    Text(
                        text = doctor.availability,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        color = Green
                    )
                }
            }
        }
    }
}
