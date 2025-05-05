package com.example.altabib.design_system.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.altabib.design.R

@Composable
fun Loading(
    innerPadding: PaddingValues = PaddingValues(),
    animationRes: Int = R.raw.health
) {
    Box(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationRes))
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.size(100.dp)
        )
    }
}
