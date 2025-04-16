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
import com.example.altabib.featuers.user.domain.entities.UserType
import com.example.altabib.navigation.screen.Screen
import com.example.altabib.navigation.utils.LocalNavController
import com.example.altabib.ui.components.AppOutlinedTextFiled
import com.example.altabib.ui.components.CitySelector
import com.example.altabib.ui.components.SELECT_CITY
import com.example.altabib.ui.theme.Gray
import com.example.altabib.ui.theme.Primary

@Composable
fun UserInfoScreen() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val navController = LocalNavController.current

    var name by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("Select City") }
    var userType by remember { mutableStateOf(UserType.Patient.key) }

    // Load Lottie Animation
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.doctor))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

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
                name = name,
                onValueChange = { name = it },
                keyboardController = keyboardController
            )

            // City Selection
            CitySelector(
                selectedCity = city,
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
                            text = type.name,
                            fontSize = 16.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            // Continue Button
            Button(
                onClick = {
                    if (name.isNotBlank() && city != SELECT_CITY) {
                        navController.navigate(Screen.Auth.createRoute(name, city, userType))
                        keyboardController?.hide()
                    } else {
                        Toast.makeText(
                            context,
                            "Please enter your name and select a city",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = "Continue",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
        }
    }
}
