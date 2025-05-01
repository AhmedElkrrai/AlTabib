package com.example.appointments.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.altabib.design.R
import com.example.altabib.design_system.components.Loading
import com.example.altabib.design_system.components.VerticalGrid
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.appointments.presentation.AppointmentsAction
import com.example.appointments.presentation.AppointmentsState

@Composable
fun AppointmentsScreen(
    modifier: Modifier = Modifier,
    state: AppointmentsState,
    onAction: (AppointmentsAction) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        if (state.isLoading) {
            Loading()
        } else if (state.appointments.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(getLocalizedString(R.string.no_appointments))
            }
        } else {
            VerticalGrid {
                itemsIndexed(state.appointments) { _, appointment ->
                    AnimatedAppointmentCard(
                        appointment = appointment,
                        onDismissClick = { onAction(AppointmentsAction.Dismiss(appointment)) }
                    )
                }
            }
        }
    }
}
