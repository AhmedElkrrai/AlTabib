package com.example.altabib.featuers.dashboard.presentation.doctor.components

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.altabib.R
import com.example.altabib.design_system.ObserveEvents
import com.example.altabib.design_system.getMessage
import com.example.altabib.featuers.dashboard.presentation.doctor.DoctorDetailsAction
import com.example.altabib.featuers.dashboard.presentation.doctor.DoctorDetailsEvent
import com.example.altabib.featuers.dashboard.presentation.doctor.DoctorDetailsViewModel
import com.example.altabib.design_system.getLocalizedString
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

            is DoctorDetailsEvent.NavigateToAddress -> {
                val gmmIntentUri = Uri.parse("geo:0,0?q=${Uri.encode(event.address)}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                    setPackage("com.google.android.apps.maps")
                }
                if (mapIntent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(mapIntent)
                } else {
                    Toast.makeText(context, "Google Maps not installed", Toast.LENGTH_SHORT).show()
                }
            }

            is DoctorDetailsEvent.Navigate -> {
                navController.navigate(event.route)
            }

            is DoctorDetailsEvent.ShowToast -> {
                Toast.makeText(context, event.error.getMessage(context), Toast.LENGTH_SHORT).show()
            }

            is DoctorDetailsEvent.ShowMessage -> {
                val message = getLocalizedString(event.msgRes)
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    DoctorDetailsScreen(
        specialization = state.doctor?.specialization?.getDisplayName()
            ?: getLocalizedString(R.string.unknown_specialization),
        state = state,
        onAction = viewModel::onAction
    )
}
