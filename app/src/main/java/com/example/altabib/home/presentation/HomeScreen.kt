package com.example.altabib.home.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.altabib.design_system.navigation.bar.DoctorBottomBar
import com.example.altabib.design_system.navigation.bar.PatientBottomBar
import com.example.altabib.design_system.utils.ForceImmersiveMode
import com.example.altabib.graph.DoctorNavGraph
import com.example.altabib.graph.PatientNavGraph
import com.example.user.domain.entities.UserType
import com.example.user.domain.usecases.GetUserUseCase

@Composable
fun HomeScreen(getUser: GetUserUseCase) {
    ForceImmersiveMode()

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
