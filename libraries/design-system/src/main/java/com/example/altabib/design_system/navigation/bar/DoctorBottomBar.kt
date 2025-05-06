package com.example.altabib.design_system.navigation.bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.altabib.design.R
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.design_system.navigation.screen.DoctorScreen

@Composable
fun DoctorBottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(
            label = getLocalizedString(R.string.analytics),
            icon = Icons.Default.ShowChart,
            route = DoctorScreen.Analytics.route
        ),
        BottomNavItem(
            label = getLocalizedString(R.string.appointments),
            icon = Icons.Default.DateRange,
            route = DoctorScreen.Appointments.route
        ),
        BottomNavItem(
            label = getLocalizedString(R.string.profile),
            icon = Icons.Default.Person,
            route = DoctorScreen.Profile.route
        )
    )

    BottomNavBar(
        items = items,
        navController = navController
    )
}
