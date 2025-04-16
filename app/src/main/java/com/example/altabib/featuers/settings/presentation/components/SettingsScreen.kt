package com.example.altabib.featuers.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.altabib.featuers.settings.presentation.SettingsAction
import com.example.altabib.featuers.settings.presentation.SettingsState
import com.example.altabib.ui.components.AppOutlinedButton
import com.example.altabib.ui.components.AppOutlinedTextFiled
import com.example.altabib.ui.components.CitySelector
import com.example.altabib.ui.components.SELECT_CITY

@Composable
fun SettingsScreen(
    state: SettingsState,
    onAction: (SettingsAction) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AppOutlinedTextFiled(
            name = state.patient?.name ?: "",
            onValueChange = { onAction(SettingsAction.UpdateName(it)) },
            keyboardController = keyboardController
        )

        CitySelector(
            selectedCity = state.patient?.city ?: SELECT_CITY,
            onCitySelected = { onAction(SettingsAction.ChangeCity(it)) }
        )

        AppOutlinedButton(
            onClick = { onAction(SettingsAction.UpdateProfile) },
            text = "Update Profile",
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppOutlinedButton(
                onClick = { onAction(SettingsAction.ContactDevs) },
                text = "Contact The Developer",
            )

            AppOutlinedButton(
                onClick = { onAction(SettingsAction.RateApp) },
                text = "Rate App",
            )
        }

        AppOutlinedButton(
            onClick = { onAction(SettingsAction.Logout) },
            text = "Logout",
        )
    }
}
