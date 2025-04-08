package com.example.altabib.navigation.utils

import androidx.navigation.NavController

fun NavController.currentRoute(): String {
    return this.currentBackStackEntry?.destination?.route ?: ""
}