package com.example.altabib.featuers.home.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.altabib.navigation.BottomNavBar
import com.example.altabib.navigation.HomeNavGraph

@Composable
fun HomeScreen(
    rootNavController: NavHostController
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }
    ) { padding ->

        HomeNavGraph(rootNavController, navController, padding)
    }
}
