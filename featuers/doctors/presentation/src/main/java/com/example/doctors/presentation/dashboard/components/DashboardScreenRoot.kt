package com.example.doctors.presentation.dashboard.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.altabib.design_system.utils.ObserveEvents
import com.example.altabib.design_system.utils.getMessage
import com.example.doctors.presentation.dashboard.DashboardEvent
import com.example.doctors.presentation.dashboard.DashboardViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreenRoot(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveEvents(events = viewModel.event) { event ->
        when (event) {
            is DashboardEvent.Navigate -> {
                navController.navigate(event.route)
            }

            is DashboardEvent.ShowToast -> {
                Toast
                    .makeText(context, event.error.getMessage(context), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    DashboardScreen(
        state = state,
        onAction = viewModel::onAction,
        modifier = modifier
    )
}
