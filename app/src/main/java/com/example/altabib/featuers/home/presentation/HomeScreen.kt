package com.example.altabib.featuers.home.presentation

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.altabib.featuers.user.domain.entities.UserType
import com.example.altabib.featuers.user.domain.usecases.GetUserUseCase
import com.example.altabib.navigation.bar.DoctorBottomBar
import com.example.altabib.navigation.bar.PatientBottomBar
import com.example.altabib.navigation.graph.DoctorNavGraph
import com.example.altabib.navigation.graph.PatientNavGraph
import com.example.altabib.utils.enableStickyImmersiveMode

@Composable
fun HomeScreen(getUser: GetUserUseCase) {
    val activity = LocalActivity.current

    LaunchedEffect(Unit) {
        activity?.enableStickyImmersiveMode()
    }

    val navController = rememberNavController()
    val userType = getUser.invoke()?.type ?: UserType.Patient

    Scaffold(
        bottomBar = {
            when (userType) {
                UserType.Patient -> PatientBottomBar(navController)
                UserType.Doctor -> DoctorBottomBar(navController)
            }
        }
    ) { padding ->
        when (userType) {
            UserType.Patient -> PatientNavGraph(
                navController = navController,
                modifier = Modifier.padding(padding)
            )

            UserType.Doctor -> DoctorNavGraph(
                navController = navController,
                modifier = Modifier.padding(padding)
            )
        }
    }
}
