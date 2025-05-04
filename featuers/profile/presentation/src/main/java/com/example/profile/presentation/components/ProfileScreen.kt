package com.example.profile.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.altabib.design.R
import com.example.altabib.design_system.components.AppOutlinedButton
import com.example.altabib.design_system.components.AppOutlinedTextFiled
import com.example.altabib.design_system.components.CitySelector
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.design_system.models.City
import com.example.altabib.design_system.theme.Primary
import com.example.altabib.design_system.utils.FormatCompose
import com.example.profile.presentation.ProfileAction
import com.example.profile.presentation.ProfileState

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    state: ProfileState,
    onAction: (ProfileAction) -> Unit
) {
    FormatCompose {
        val scrollState = rememberScrollState()

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            // Profile photo + name
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable { onAction(ProfileAction.OnOpenImagePicker) }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.doctor), // Placeholder
                    contentDescription = "Doctor's photo",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                )

                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit avatar",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .background(Color.White, CircleShape)
                        .padding(4.dp)
                        .size(20.dp)
                )
            }

            Spacer(Modifier.height(16.dp))
            // Name
            AppOutlinedTextFiled(
                value = state.doctor.name,
                onValueChange = { onAction(ProfileAction.OnNameChange(it)) },
                label = getLocalizedString(R.string.name),
                leadingIcon = { Icon(Icons.Default.Person, null) },
            )

            Spacer(Modifier.height(12.dp))

            // Bio
            AppOutlinedTextFiled(
                value = state.doctor.bio,
                onValueChange = { onAction(ProfileAction.OnBioChange(it)) },
                label = getLocalizedString(R.string.bio),
                placeholder = { Text(getLocalizedString(R.string.bio_hint)) },
                leadingIcon = { Icon(Icons.Default.Info, null) },
            )

            Spacer(Modifier.height(12.dp))

            // Price
            AppOutlinedTextFiled(
                value = state.doctor.price.toString(),
                onValueChange = { onAction(ProfileAction.OnPriceChange(it)) },
                label = getLocalizedString(R.string.price),
                leadingIcon = { Icon(Icons.Default.AttachMoney, null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(Modifier.height(12.dp))

            // City
            CitySelector(
                selectedCity = City.displayName(state.doctor.city),
                onCitySelected = { onAction(ProfileAction.OnCityChange(it)) }
            )

            Spacer(Modifier.height(12.dp))

            // Address
            AppOutlinedTextFiled(
                value = state.doctor.address,
                onValueChange = { onAction(ProfileAction.OnAddressChange(it)) },
                label = getLocalizedString(R.string.address),
                leadingIcon = { Icon(Icons.Default.Place, null) },
            )

            Spacer(Modifier.height(12.dp))

            // Contact
            AppOutlinedTextFiled(
                value = state.doctor.contact,
                onValueChange = { onAction(ProfileAction.OnContactChange(it)) },
                label = getLocalizedString(R.string.contact),
                leadingIcon = { Icon(Icons.Default.Call, null) },
            )

            Spacer(Modifier.height(12.dp))

            // Availability (Chips or summary + edit button)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Schedule, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = getLocalizedString(R.string.available),
                    modifier = Modifier.weight(1f)
                )
                TextButton(onClick = { onAction(ProfileAction.OnEditAvailabilityClick) }) {
                    Text(
                        text = getLocalizedString(R.string.edit),
                        color = Primary
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // Save button
            AppOutlinedButton(
                text = getLocalizedString(R.string.save_profile),
                onClick = { onAction(ProfileAction.OnSaveClick) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            HorizontalDivider()

            Spacer(Modifier.height(12.dp))

            // Language / Contact Dev / Logout
            TextButton(
                onClick = { onAction(ProfileAction.OnChangeLanguageClick) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = getLocalizedString(R.string.change_language),
                    color = Color.White
                )
            }

            TextButton(
                onClick = { onAction(ProfileAction.OnContactDeveloperClick) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = getLocalizedString(R.string.contact_devs),
                    color = Color.White
                )
            }

            TextButton(
                onClick = { onAction(ProfileAction.OnLogoutClick) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.textButtonColors(contentColor = Color.Red)
            ) {
                Text(
                    text = getLocalizedString(R.string.logout),
                    color = Color.Red
                )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}
