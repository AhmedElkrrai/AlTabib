package com.example.altabib.featuers.dashboard.presentation.specialization.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.altabib.core.presentation.util.ObserveEvents
import com.example.altabib.core.presentation.util.getMessage
import com.example.altabib.featuers.dashboard.domain.entities.Specialization
import com.example.altabib.featuers.dashboard.presentation.specialization.SpecializationAction
import com.example.altabib.featuers.dashboard.presentation.specialization.SpecializationEvent
import com.example.altabib.featuers.dashboard.presentation.specialization.SpecializationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SpecializationScreenRoot(
    key: String,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: SpecializationViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key) {
        viewModel.onAction(SpecializationAction.LoadDoctors(key))
    }

    ObserveEvents(events = viewModel.event) { event ->
        when (event) {
            is SpecializationEvent.Back -> {
                navController.popBackStack()
            }

            is SpecializationEvent.Navigate -> {
                navController.navigate(event.route)
            }

            is SpecializationEvent.ShowToast -> {
                Toast
                    .makeText(context, event.error.getMessage(context), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    SpecializationScreen(
        specialization = Specialization.fromKey(key).getDisplayName(),
        state = state,
        onAction = viewModel::onAction,
        modifier = modifier
    )
}
