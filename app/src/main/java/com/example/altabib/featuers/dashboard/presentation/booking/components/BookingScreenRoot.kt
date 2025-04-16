package com.example.altabib.featuers.dashboard.presentation.booking.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.altabib.core.presentation.util.ObserveEvents
import com.example.altabib.core.presentation.util.getMessage
import com.example.altabib.featuers.dashboard.presentation.booking.BookingAction
import com.example.altabib.featuers.dashboard.presentation.booking.BookingEvent
import com.example.altabib.featuers.dashboard.presentation.booking.BookingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookingScreenRoot(
    doctorId: String,
    navController: NavHostController,
    viewModel: BookingViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(doctorId) {
        viewModel.onAction(BookingAction.LoadDoctor(doctorId))
    }

    ObserveEvents(events = viewModel.event) { event ->
        when (event) {
            is BookingEvent.Back -> {
                navController.popBackStack()
            }

            is BookingEvent.ShowToast -> {
                Toast
                    .makeText(context, event.error.getMessage(context), Toast.LENGTH_SHORT)
                    .show()
            }

            is BookingEvent.ShowMessage -> {
                Toast
                    .makeText(context, stringResource(event.msgRes), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    BookingScreen(
        state = state,
        onAction = viewModel::onAction
    )
}
