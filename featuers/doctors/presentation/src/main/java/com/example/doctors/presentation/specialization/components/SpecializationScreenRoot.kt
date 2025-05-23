package com.example.doctors.presentation.specialization.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.altabib.design_system.models.Specialization
import com.example.altabib.design_system.utils.ObserveEvents
import com.example.altabib.design_system.utils.getMessage
import com.example.doctors.presentation.specialization.SpecializationAction
import com.example.doctors.presentation.specialization.SpecializationEvent
import com.example.doctors.presentation.specialization.SpecializationViewModel
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
        specialization = Specialization.displayName(key),
        state = state,
        onAction = viewModel::onAction,
        modifier = modifier
    )
}
