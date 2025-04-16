package com.example.altabib.featuers.settings.presentation.components

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.altabib.MainActivity
import com.example.altabib.core.presentation.util.ObserveEvents
import com.example.altabib.core.presentation.util.getMessage
import com.example.altabib.featuers.contact_us.ContactUsDialog
import com.example.altabib.featuers.settings.presentation.SettingsAction
import com.example.altabib.featuers.settings.presentation.SettingsEvent
import com.example.altabib.featuers.settings.presentation.SettingsViewModel
import com.example.altabib.navigation.screen.Screen
import com.example.altabib.navigation.utils.LocalNavController
import com.example.altabib.utils.LocaleHelper
import com.example.altabib.utils.getLocalizedString
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreenRoot(
    viewModel: SettingsViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    val rootNavController = LocalNavController.current
    var showContactDialog by remember { mutableStateOf(false) }

    if (showContactDialog) {
        ContactUsDialog { showContactDialog = false }
    }

    LaunchedEffect(Unit) {
        viewModel.onAction(SettingsAction.InitPatientData)
    }

    ObserveEvents(events = viewModel.event) { event ->
        when (event) {
            is SettingsEvent.ShowMessage -> {
                val message = getLocalizedString(context, event.msgRes)
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }

            is SettingsEvent.LoggedOut -> {
                rootNavController.navigate(Screen.UserInfo.route) {
                    popUpTo(0) { inclusive = true }
                }
            }

            is SettingsEvent.ShowToast -> {
                Toast
                    .makeText(context, event.error.getMessage(context), Toast.LENGTH_SHORT)
                    .show()
            }

            is SettingsEvent.RateApp -> {
                val packageName = context.packageName
                val playStoreUri = Uri.parse("market://details?id=$packageName")
                val fallbackUri =
                    Uri.parse("https://play.google.com/store/apps/details?id=$packageName")

                val intent = Intent(Intent.ACTION_VIEW, playStoreUri).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }

                try {
                    context.startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    // Fallback to browser if Play Store not available
                    context.startActivity(
                        Intent(Intent.ACTION_VIEW, fallbackUri).apply {
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        }
                    )
                }
            }

            is SettingsEvent.ContactDevs -> {
                showContactDialog = true
            }

            is SettingsEvent.ChangeAppLanguage -> {
                val updatedContext = LocaleHelper.setLocale(context, event.language)
                val intent = Intent(updatedContext, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                updatedContext.startActivity(intent)
            }
        }
    }

    SettingsScreen(
        state = state,
        onAction = viewModel::onAction
    )
}
