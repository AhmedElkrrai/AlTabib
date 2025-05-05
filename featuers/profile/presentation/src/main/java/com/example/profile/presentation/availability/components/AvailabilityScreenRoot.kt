package com.example.profile.presentation.availability.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.altabib.design_system.utils.ObserveEvents
import com.example.altabib.design_system.utils.getMessage
import com.example.profile.presentation.availability.AvailabilityEvent
import com.example.profile.presentation.availability.AvailabilityViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AvailabilityScreenRoot(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: AvailabilityViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveEvents(
        events = viewModel.event
    ) { event ->
        when (event) {
            is AvailabilityEvent.Back -> navController.popBackStack()
            is AvailabilityEvent.ShowToast -> {
                Toast
                    .makeText(context, event.error.getMessage(context), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    EditAvailabilityScreen(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction
    )
}
