package com.example.appointments.presentation.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.design_system.utils.ObserveEvents
import com.example.altabib.design_system.utils.getMessage
import com.example.appointments.presentation.AppointmentsEvent
import com.example.appointments.presentation.AppointmentsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppointmentsScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: AppointmentsViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveEvents(events = viewModel.event) { event ->
        when (event) {
            is AppointmentsEvent.ShowToast -> {
                Toast
                    .makeText(context, event.error.getMessage(context), Toast.LENGTH_SHORT)
                    .show()
            }

            is AppointmentsEvent.ShowMessage -> {
                val message = getLocalizedString(event.msgRes)
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    AppointmentsScreen(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction
    )
}
