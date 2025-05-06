package com.example.profile.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.altabib.design.R
import com.example.altabib.design_system.components.AppOutlinedButton
import com.example.altabib.design_system.components.AppOutlinedTextFiled
import com.example.altabib.design_system.components.CitySelector
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.design_system.models.City
import com.example.altabib.design_system.models.Specialization
import com.example.altabib.design_system.theme.Primary
import com.example.altabib.design_system.utils.ForceImmersiveMode
import com.example.altabib.design_system.utils.FormatCompose
import com.example.profile.presentation.profile.actions.ProfileAction
import com.example.profile.presentation.profile.state.ProfileState

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    state: ProfileState,
    sendAction: (ProfileAction) -> Unit
) {
    FormatCompose {
        ForceImmersiveMode()
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Profile photo + name
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable { sendAction(ProfileAction.OnOpenImagePicker) }
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = state.doctor.avatar,
                        placeholder = painterResource(R.drawable.doctor),
                        error = painterResource(R.drawable.doctor)
                    ),
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
                onValueChange = { sendAction(ProfileAction.OnNameChange(it)) },
                label = getLocalizedString(R.string.name),
                leadingIcon = { Icon(Icons.Default.Person, null) },
            )

            Spacer(Modifier.height(12.dp))

            // Bio
            AppOutlinedTextFiled(
                value = state.doctor.bio,
                onValueChange = { sendAction(ProfileAction.OnBioChange(it)) },
                label = getLocalizedString(R.string.bio),
                placeholder = { Text(getLocalizedString(R.string.bio_hint)) },
                leadingIcon = { Icon(Icons.Default.Info, null) },
            )

            Spacer(Modifier.height(12.dp))

            // Price
            AppOutlinedTextFiled(
                value = state.doctor.price.toString(),
                onValueChange = { sendAction(ProfileAction.OnPriceChange(it)) },
                label = getLocalizedString(R.string.price),
                leadingIcon = { Icon(Icons.Default.AttachMoney, null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(Modifier.height(12.dp))

            // City
            CitySelector(
                selectedCity = City.displayName(state.doctor.city),
                onCitySelected = { sendAction(ProfileAction.OnCityChange(it)) }
            )

            Spacer(Modifier.height(12.dp))

            // Address
            AppOutlinedTextFiled(
                value = state.doctor.address,
                onValueChange = { sendAction(ProfileAction.OnAddressChange(it)) },
                label = getLocalizedString(R.string.address),
                leadingIcon = { Icon(Icons.Default.Place, null) },
            )

            Spacer(Modifier.height(12.dp))

            // Contact
            AppOutlinedTextFiled(
                value = state.doctor.contact,
                onValueChange = { sendAction(ProfileAction.OnContactChange(it)) },
                label = getLocalizedString(R.string.contact),
                leadingIcon = { Icon(Icons.Default.Call, null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(Modifier.height(12.dp))

            // Specialization
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { sendAction(ProfileAction.OnSpecializationClick) }
                    .padding(vertical = 8.dp)
            ) {
                Icon(Icons.Default.Work, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = Specialization.displayName(state.doctor.specKey),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }

            Spacer(Modifier.height(12.dp))

            // In Queue
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Icon(Icons.Default.Person, contentDescription = null)

                Spacer(Modifier.width(8.dp))

                Text(
                    text = getLocalizedString(R.string.in_queue),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.ArrowDownward,
                    modifier = Modifier
                        .clickable {
                            if (state.doctor.inQueue > 0) {
                                sendAction(
                                    ProfileAction.OnQueueChanged(state.doctor.inQueue - 1)
                                )
                            }
                        },
                    contentDescription = null
                )

                Text(
                    text = state.doctor.inQueue.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = Icons.Default.ArrowUpward,
                    modifier = Modifier
                        .clickable { sendAction(ProfileAction.OnQueueChanged(state.doctor.inQueue + 1)) },
                    contentDescription = null
                )
            }

            Spacer(Modifier.height(12.dp))

            // Availability
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Schedule, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                val textRes = if (
                    state.doctor.availability.days.isEmpty()
                    && state.doctor.availability.hours.isEmpty()
                ) R.string.not_available
                else R.string.available

                Text(
                    text = getLocalizedString(textRes),
                    modifier = Modifier.weight(1f)
                )
                TextButton(onClick = { sendAction(ProfileAction.OnEditAvailabilityClick) }) {
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
                onClick = { sendAction(ProfileAction.OnSaveClick(state.doctor)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            HorizontalDivider()

            Spacer(Modifier.height(12.dp))

            // Language / Contact Dev / Logout
            TextButton(
                onClick = { sendAction(ProfileAction.OnChangeLanguageClick) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = getLocalizedString(R.string.change_language),
                    color = Color.White
                )
            }

            TextButton(
                onClick = { sendAction(ProfileAction.OnContactDeveloperClick) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = getLocalizedString(R.string.contact_devs),
                    color = Color.White
                )
            }

            TextButton(
                onClick = { sendAction(ProfileAction.OnLogoutClick) },
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
