package com.example.altabib.featuers.dashboard.presentation.doctor.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.altabib.R
import com.example.altabib.featuers.dashboard.presentation.doctor.DoctorDetailsAction
import com.example.altabib.featuers.dashboard.presentation.doctor.DoctorDetailsState
import com.example.altabib.ui.components.TopAppBarWithBackButton
import com.example.altabib.ui.theme.LightBlue

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
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
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
                                painter = painterResource(id = R.drawable.ic_google_logo), // Placeholder
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
                                    tint = LightBlue,
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
                                text = "${"%.1f".format(animatedRating.value)} (${doctor.reviews} reviews)",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    InfoRow(Icons.Default.Info, "Bio: ${doctor.bio}")
                    InfoRow(Icons.Default.Schedule, "Available: ${doctor.availability}")
                    InfoRow(Icons.Default.People, "Queue: ${doctor.inQueue} people")
                    ClickableInfoRow(
                        icon = Icons.Default.Place,
                        text = doctor.address,
                        onClick = { onAction(DoctorDetailsAction.OnAddressClick(doctor.address)) }
                    )
                    InfoRow(Icons.Default.AttachMoney, "Price: ${doctor.price} LE")

                    Spacer(modifier = Modifier.height(24.dp))

                    ReviewSection { onAction(it) }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Action buttons
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {

                        OutlinedButton(
                            onClick = { onAction(DoctorDetailsAction.OnBookAppointmentClick) },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color.White,
                                containerColor = LightBlue
                            ),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
                        ) {
                            Text("Book Now")
                        }

                        OutlinedButton(
                            onClick = { onAction(DoctorDetailsAction.OnAddToFavoritesClick) },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color.White,
                                containerColor = LightBlue
                            ),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
                        ) {
                            Text("Add to Favorites")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
