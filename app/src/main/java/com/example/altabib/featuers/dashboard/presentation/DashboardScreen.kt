package com.example.altabib.featuers.dashboard.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun DashboardScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Text("Welcome to the Dashboard", modifier = Modifier.fillMaxSize())
}
