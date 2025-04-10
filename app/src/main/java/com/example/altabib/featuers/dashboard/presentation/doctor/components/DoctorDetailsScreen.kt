package com.example.altabib.featuers.dashboard.presentation.doctor.components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
                    val padding = 16.dp

                    Spacer(modifier = Modifier.height(padding))

                    // Avatar, Specialization & Rating
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
                                text = "${doctor.rating} (${doctor.reviews} reviews)",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Info Items
                    val textStyle = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    InfoRow(icon = Icons.Default.Info, text = doctor.bio, style = textStyle)
                    Spacer(modifier = Modifier.height(padding))

                    InfoRow(
                        icon = Icons.Default.Schedule,
                        text = "Available: ${doctor.availability}",
                        style = textStyle
                    )
                    Spacer(modifier = Modifier.height(padding))

                    InfoRow(
                        icon = Icons.Default.People,
                        text = "Queue: ${doctor.inQueue} people",
                        style = textStyle
                    )
                    Spacer(modifier = Modifier.height(padding))

                    InfoRow(
                        icon = Icons.Default.AttachMoney,
                        text = "Price: ${doctor.price} LE",
                        style = textStyle
                    )

                    Spacer(modifier = Modifier.height(padding))

                    ClickableInfoRow(
                        icon = Icons.Default.Place,
                        text = doctor.address,
                        style = textStyle,
                        onClick = { onAction(DoctorDetailsAction.OnAddressClick(doctor.address)) }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Action Buttons
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = { onAction(DoctorDetailsAction.OnBookAppointmentClick) },
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp),
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
                            modifier = Modifier
                                .weight(1f)
                                .height(56.dp),
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
