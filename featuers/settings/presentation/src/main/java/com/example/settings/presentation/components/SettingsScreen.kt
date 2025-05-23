package com.example.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.altabib.design.R
import com.example.settings.presentation.SettingsAction
import com.example.settings.presentation.SettingsState
import com.example.altabib.design_system.models.City
import com.example.altabib.design_system.components.AppOutlinedButton
import com.example.altabib.design_system.components.AppOutlinedTextFiled
import com.example.altabib.design_system.components.CitySelector
import com.example.altabib.design_system.utils.FormatCompose
import com.example.altabib.design_system.localization.LocaleHelper
import com.example.altabib.design_system.localization.getLocalizedString

@Composable
fun SettingsScreen(
    state: SettingsState,
    onAction: (SettingsAction) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        FormatCompose {
            AppOutlinedTextFiled(
                value = state.patient?.name ?: "",
                onValueChange = { onAction(SettingsAction.UpdateName(it)) },
                label = getLocalizedString(R.string.name),
                keyboardController = keyboardController,
                initialCursorPosition = state.patient?.name?.length
            )
        }

        CitySelector(
            selectedCity = City.displayName(state.patient?.city),
            onCitySelected = { onAction(SettingsAction.ChangeCity(it)) }
        )

        AppOutlinedButton(
            onClick = { onAction(SettingsAction.UpdateProfile) },
            text = getLocalizedString(R.string.update_profile),
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val currentLang = LocaleHelper.getCurrentLanguageEnum(LocalContext.current)

            AppOutlinedButton(
                onClick = { onAction(SettingsAction.ChangeLanguage(LocaleHelper.Language.ENGLISH)) },
                text = getLocalizedString(R.string.english),
                enabled = currentLang != LocaleHelper.Language.ENGLISH
            )

            Spacer(modifier = Modifier.padding(8.dp))

            AppOutlinedButton(
                onClick = { onAction(SettingsAction.ChangeLanguage(LocaleHelper.Language.ARABIC)) },
                text = getLocalizedString(R.string.arabic),
                enabled = currentLang != LocaleHelper.Language.ARABIC
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppOutlinedButton(
                onClick = { onAction(SettingsAction.ContactDevs) },
                text = getLocalizedString(R.string.contact_devs),
            )

            Spacer(modifier = Modifier.padding(6.dp))

            AppOutlinedButton(
                onClick = { onAction(SettingsAction.RateApp) },
                text = getLocalizedString(R.string.rate_app),
            )
        }

        AppOutlinedButton(
            onClick = { onAction(SettingsAction.Logout) },
            text = getLocalizedString(R.string.logout),
        )
    }
}
