package com.example.doctors.presentation.doctor.components

import android.content.ClipData.newPlainText
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.design_system.models.Specialization
import com.example.altabib.design_system.utils.ObserveEvents
import com.example.altabib.design_system.utils.getMessage
import com.example.altabib.design.R
import com.example.doctors.presentation.doctor.DoctorDetailsAction
import com.example.doctors.presentation.doctor.DoctorDetailsEvent
import com.example.doctors.presentation.doctor.DoctorDetailsViewModel
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
        viewModel.onAction(DoctorDetailsAction.UpdateProfileViews(doctorId))
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

            is DoctorDetailsEvent.CopyContact -> {
                val clipboardManager =
                    context.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                val clipData = newPlainText("contact", event.contact)
                clipboardManager.setPrimaryClip(clipData)
            }
        }
    }

    DoctorDetailsScreen(
        specialization = Specialization.displayName(state.doctor?.specKey),
        state = state,
        onAction = viewModel::onAction
    )
}
