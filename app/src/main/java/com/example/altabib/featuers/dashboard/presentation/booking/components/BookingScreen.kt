package com.example.altabib.featuers.dashboard.presentation.booking.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.altabib.featuers.dashboard.presentation.booking.BookingAction
import com.example.altabib.featuers.dashboard.presentation.booking.BookingState
import com.example.altabib.featuers.dashboard.presentation.doctor.components.RatingSection
import com.example.altabib.ui.components.Loading
import com.example.altabib.ui.components.TopAppBarWithBackButton

@Composable
fun BookingScreen(
    state: BookingState,
    onAction: (BookingAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBarWithBackButton(
                title = state.doctor?.name ?: "",
                onBackClick = { onAction(BookingAction.OnBackClick) }
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
                        .verticalScroll(rememberScrollState()),
                ) {

                    Spacer(modifier = Modifier.height(8.dp))

                    DatePicker(
                        state = state,
                        onAction = onAction
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Rating section
                    RatingSection(state.userRating) {
                        onAction(BookingAction.OnSubmitRating(it))
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // User review input
                    OutlinedTextField(
                        value = state.userReview,
                        onValueChange = { onAction(BookingAction.OnReviewTextChanged(it)) },
                        label = { Text("Your review") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = false,
                        maxLines = 3
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Leave review button
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                onAction(
                                    BookingAction.OnSubmitReview
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .height(48.dp)
                        ) {
                            Text("Leave a review")
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Reviews
                    if (doctor.reviewsList.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(170.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "No reviews yet",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    } else {
                        Text(
                            text = "What others are saying:",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                        ) {
                            itemsIndexed(
                                doctor
                                    .reviewsList
                                    .sortedByDescending { it.createdAt })
                            { _, review ->
                                ReviewCard(review)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Submit button
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                onAction(BookingAction.OnConfirmBooking)
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .height(48.dp)
                        ) {
                            Text("Confirm Booking")
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
