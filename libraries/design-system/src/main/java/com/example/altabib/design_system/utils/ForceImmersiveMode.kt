package com.example.altabib.design_system.utils

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable

@Composable
fun ForceImmersiveMode() {
    val activity = LocalActivity.current

    KeyboardDismissListener {
        activity?.enableStickyImmersiveMode()
    }
}