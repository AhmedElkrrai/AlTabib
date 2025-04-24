package com.example.altabib.design_system.navigation.utils

import androidx.navigation.NavController

fun NavController.currentRoute(): String {
    return this.currentBackStackEntry?.destination?.route ?: ""
}