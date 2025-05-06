package com.example.profile.presentation.profile.components

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.altabib.design_system.components.ContactUsDialog
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.design_system.navigation.screen.Screen
import com.example.altabib.design_system.navigation.utils.LocalNavController
import com.example.altabib.design_system.utils.ObserveEvents
import com.example.altabib.design_system.utils.getMessage
import com.example.profile.presentation.profile.actions.ProfileAction
import com.example.profile.presentation.profile.state.ProfileEvent
import com.example.profile.presentation.profile.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreenRoot(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onLanguageChanged: @Composable () -> Unit,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    val rootNavController = LocalNavController.current
    var showContactDialog by remember { mutableStateOf(false) }
    var showSpecializationDialog by remember { mutableStateOf(false) }

    if (showContactDialog) {
        ContactUsDialog { showContactDialog = false }
    }

    if (showSpecializationDialog) {
        SpecializationDialog(
            current = state.doctor.specKey,
            onDismiss = { showSpecializationDialog = false },
            onSelect = {
                viewModel.sendAction(ProfileAction.OnSpecializationSelected(it))
                showSpecializationDialog = false
            }
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.sendAction(ProfileAction.OnAvatarSelected(it, state.doctor))
        }
    }

    ObserveEvents(events = viewModel.event) { event ->
        when (event) {
            is ProfileEvent.ShowToast -> {
                Toast
                    .makeText(context, event.error.getMessage(context), Toast.LENGTH_SHORT)
                    .show()
            }

            is ProfileEvent.ShowMessage -> {
                val message = getLocalizedString(event.msgRes)
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }

            is ProfileEvent.Logout -> {
                rootNavController.navigate(Screen.UserInfo.route) {
                    popUpTo(0) { inclusive = true }
                }
            }

            is ProfileEvent.ContactDevs -> {
                showContactDialog = true
            }

            is ProfileEvent.LanguageChanged -> {
                onLanguageChanged()
            }

            is ProfileEvent.OpenSpecializationDialog -> {
                showSpecializationDialog = true
            }

            is ProfileEvent.OpenImagePicker -> {
                launcher.launch("image/*")
            }

            is ProfileEvent.Navigate -> {
                navController.navigate(event.route)
            }
        }
    }

    ProfileScreen(
        modifier = modifier,
        state = state,
        sendAction = viewModel::sendAction
    )
}
