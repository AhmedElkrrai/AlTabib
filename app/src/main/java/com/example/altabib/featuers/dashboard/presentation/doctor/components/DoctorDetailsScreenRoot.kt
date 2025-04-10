package com.example.altabib.featuers.dashboard.presentation.doctor.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.altabib.R
import com.example.altabib.core.presentation.util.ObserveEvents
import com.example.altabib.core.presentation.util.getMessage
import com.example.altabib.featuers.dashboard.domain.entities.getDisplayName
import com.example.altabib.featuers.dashboard.presentation.doctor.DoctorDetailsAction
import com.example.altabib.featuers.dashboard.presentation.doctor.DoctorDetailsEvent
import com.example.altabib.featuers.dashboard.presentation.doctor.DoctorDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DoctorDetailsScreenRoot(
    doctorId: String,
    navController: NavHostController,
    viewModel: DoctorDetailsViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(doctorId) {
        viewModel.onAction(DoctorDetailsAction.LoadDoctor(doctorId))
    }

    ObserveEvents(events = viewModel.event) { event ->
        when (event) {
            is DoctorDetailsEvent.Back -> {
                navController.popBackStack()
            }

            is DoctorDetailsEvent.ShowToast -> {
                Toast.makeText(context, event.error.getMessage(context), Toast.LENGTH_SHORT).show()
            }
        }
    }

    DoctorDetailsScreen(
        specialization = state.doctor?.specialization?.getDisplayName(context)
            ?: stringResource(R.string.unknown_specialization),
        state = state,
        onAction = viewModel::onAction
    )
}
