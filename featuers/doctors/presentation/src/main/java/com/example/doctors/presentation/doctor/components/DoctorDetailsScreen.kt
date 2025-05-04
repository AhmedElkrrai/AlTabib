package com.example.doctors.presentation.doctor.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.altabib.design.R
import com.example.altabib.design_system.components.AppOutlinedButton
import com.example.altabib.design_system.components.Loading
import com.example.altabib.design_system.components.TopAppBarWithBackButton
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.design_system.localization.getRatingText
import com.example.altabib.design_system.theme.Primary
import com.example.altabib.design_system.utils.FormatCompose
import com.example.doctors.presentation.doctor.DoctorDetailsAction
import com.example.doctors.presentation.doctor.DoctorDetailsState
import com.example.doctors.presentation.doctor.util.format

@Composable
fun DoctorDetailsScreen(
    specialization: String,
    state: DoctorDetailsState,
    onAction: (DoctorDetailsAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val animatedRating = animateFloatAsState(
        targetValue = state.doctor?.rating ?: 0f,
        animationSpec = tween(durationMillis = 500),
        label = "rating"
    )

    Scaffold(
        topBar = {
            TopAppBarWithBackButton(
                title = state.doctor?.name ?: "",
                onBackClick = { onAction(DoctorDetailsAction.OnBackClick) }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        if (state.isLoading) {
            Loading(innerPadding)
        } else {
            state.doctor?.let { doctor ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    // Avatar & badge
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier.size(180.dp),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.doctor), // Placeholder
                                contentDescription = "Doctor's photo",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                                    .border(2.dp, Color.Gray, CircleShape)
                            )

                            if (doctor.premium) {
                                Icon(
                                    imageVector = Icons.Default.Verified,
                                    contentDescription = "Verified Badge",
                                    tint = Primary,
                                    modifier = Modifier
                                        .offset(x = (-16).dp, y = (-2).dp)
                                        .size(36.dp)
                                        .padding(2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = specialization,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(4.dp))

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
                                text = getRatingText(animatedRating.value, doctor.reviews),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }

                    FormatCompose {
                        Spacer(modifier = Modifier.height(16.dp))

                        InfoRow(
                            icon = Icons.Default.Info,
                            text = getLocalizedString(R.string.bio) + ": ${doctor.bio}"
                        )

                        AvailabilityChips(doctor.availability)

                        InfoRow(
                            icon = Icons.Default.People,
                            text = getLocalizedString(R.string.queue) + ": ${doctor.inQueue}"
                        )

                        ClickableInfoRow(
                            icon = Icons.Default.Place,
                            text = doctor.address,
                            onClick = { onAction(DoctorDetailsAction.OnAddressClick(doctor.address)) }
                        )

                        val priceText = getLocalizedString(R.string.price) +
                                ": ${doctor.price} " +
                                getLocalizedString(R.string.currency)

                        InfoRow(
                            icon = Icons.Default.AttachMoney,
                            text = priceText
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Action buttons
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                        ) {

                            AppOutlinedButton(
                                modifier = Modifier.weight(1f),
                                text = getLocalizedString(R.string.book_now),
                                onClick = {
                                    onAction(
                                        DoctorDetailsAction.OnBookAppointmentClick(state.doctor.id)
                                    )
                                }
                            )

                            AppOutlinedButton(
                                modifier = Modifier.weight(1f),
                                text = getLocalizedString(R.string.add_to_fav),
                                onClick = {
                                    onAction(DoctorDetailsAction.OnAddToFavoritesClick)
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}
