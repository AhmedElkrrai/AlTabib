package com.example.altabib.featuers.user.presentation.info

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.altabib.R
import com.example.altabib.design_system.models.City
import com.example.altabib.featuers.user.domain.entities.UserType
import com.example.altabib.design_system.navigation.screen.Screen
import com.example.altabib.design_system.navigation.utils.LocalNavController
import com.example.altabib.design_system.components.AppOutlinedTextFiled
import com.example.altabib.design_system.components.CitySelector
import com.example.altabib.design_system.theme.Gray
import com.example.altabib.design_system.theme.Primary
import com.example.altabib.design_system.utils.FormatCompose
import com.example.altabib.design_system.localization.getLocalizedString

@Composable
fun UserInfoScreen() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val navController = LocalNavController.current

    val defaultCityTxt = getLocalizedString(R.string.select_city)
    var name by remember { mutableStateOf("") }
    var city by remember { mutableStateOf(defaultCityTxt) }
    var userType by remember { mutableStateOf(UserType.Patient.key) }

    // Load Lottie Animation
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.doctor))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    FormatCompose {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Animation
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                LottieAnimation(
                    composition = composition,
                    progress = { progress }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // User Name
                AppOutlinedTextFiled(
                    value = name,
                    onValueChange = { name = it },
                    label = getLocalizedString(R.string.name),
                    keyboardController = keyboardController
                )

                // City Selection
                CitySelector(
                    selectedCity = City.displayName(city),
                    onCitySelected = { city = it }
                )

                // User Type Selection
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    listOf(UserType.Doctor, UserType.Patient).forEach { type ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (userType == type.key) Primary else Gray)
                                .clickable {
                                    userType = type.key
                                    keyboardController?.hide()
                                }
                                .padding(vertical = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = type.displayName(),
                                fontSize = 16.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                // Continue Button
                val msg = getLocalizedString(R.string.empty_input)
                Button(
                    onClick = {
                        if (name.isNotBlank() && city != defaultCityTxt) {
                            navController.navigate(Screen.Auth.createRoute(name, city, userType))
                            keyboardController?.hide()
                        } else {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = getLocalizedString(R.string.continue_txt),
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }
            }
        }
    }
}
