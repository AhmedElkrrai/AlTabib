package com.example.appointments.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.appointments.presentation.models.AppointmentUi
import kotlinx.coroutines.delay

@Composable
fun AnimatedAppointmentCard(
    modifier: Modifier = Modifier,
    appointment: AppointmentUi,
    onDismissClick: () -> Unit
) {
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(isVisible) {
        if (!isVisible) {
            delay(300)
            onDismissClick()
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        exit = fadeOut() + slideOutHorizontally(targetOffsetX = { it / 2 }),
        modifier = modifier
    ) {
        AppointmentCard(
            appointment = appointment,
            onDismissClick = {
                isVisible = false
            }
        )
    }
}
