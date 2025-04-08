package com.example.altabib.featuers.settings.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.altabib.core.presentation.util.ObserveEvents
import com.example.altabib.core.presentation.util.getMessage
import com.example.altabib.navigation.utils.LocalNavController
import com.example.altabib.navigation.screen.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreenRoot(
    navController: NavHostController,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    val rootNavController = LocalNavController.current

    ObserveEvents(events = viewModel.event) { event ->
        when (event) {
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
        }
    }

    SettingsScreen(
        state = state,
        onAction = viewModel::onAction
    )
}
