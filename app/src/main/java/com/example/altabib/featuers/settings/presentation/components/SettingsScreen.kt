package com.example.altabib.featuers.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.altabib.featuers.settings.presentation.SettingsAction
import com.example.altabib.featuers.settings.presentation.SettingsState
import com.example.altabib.ui.components.AppOutlinedButton

@Composable
fun SettingsScreen(
    state: SettingsState,
    onAction: (SettingsAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AppOutlinedButton(
            onClick = { onAction(SettingsAction.ContactDevs) },
            text = "Contact The Developer",
        )

        AppOutlinedButton(
            onClick = { onAction(SettingsAction.EditProfile) },
            text = "Edit Profile",
        )

        AppOutlinedButton(
            onClick = { onAction(SettingsAction.RateApp) },
            text = "Rate App",
        )

        AppOutlinedButton(
            onClick = { onAction(SettingsAction.Logout) },
            text = "Logout",
        )
    }
}
