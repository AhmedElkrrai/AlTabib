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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.altabib.R
import com.example.altabib.featuers.dashboard.presentation.booking.BookingAction
import com.example.altabib.featuers.dashboard.presentation.booking.BookingState
import com.example.altabib.featuers.dashboard.presentation.doctor.components.RatingSection
import com.example.altabib.ui.components.AppOutlinedButton
import com.example.altabib.ui.components.AppOutlinedTextFiled
import com.example.altabib.ui.components.Loading
import com.example.altabib.ui.components.TopAppBarWithBackButton
import com.example.altabib.design_system.FormatCompose
import com.example.altabib.design_system.getLocalizedString

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
        FormatCompose {
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
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(4.dp))

                        DatePicker(
                            state = state,
                            onAction = onAction
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Rating section
                        RatingSection(state.userRating) {
                            onAction(BookingAction.OnSubmitRating(it))
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // User review input
                        AppOutlinedTextFiled(
                            value = state.userReview,
                            label = getLocalizedString(R.string.submit_review),
                            onValueChange = { onAction(BookingAction.OnReviewTextChanged(it)) },
                            keyboardController = LocalSoftwareKeyboardController.current,
                            singleLine = false,
                            maxLines = 3,
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // Leave review button
                        AppOutlinedButton(
                            text = getLocalizedString(R.string.leave_review),
                            onClick = {
                                onAction(BookingAction.OnSubmitReview)
                            },
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Reviews
                        if (doctor.reviewsList.isEmpty()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(170.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = getLocalizedString(R.string.no_reviews),
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        } else {
                            Text(
                                text = getLocalizedString(R.string.reviews_title),
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
                        AppOutlinedButton(
                            text = getLocalizedString(R.string.confirm_booking),
                            onClick = {
                                onAction(BookingAction.OnConfirmBooking)
                            },
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}
