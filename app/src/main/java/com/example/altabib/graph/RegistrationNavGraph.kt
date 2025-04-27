package com.example.altabib.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.altabib.featuers.home.presentation.HomeScreen
import com.example.altabib.featuers.user.presentation.auth.components.AuthScreenRoot
import com.example.altabib.featuers.user.presentation.info.UserInfoScreen
import com.example.altabib.core.CITY
import com.example.altabib.core.NAME
import com.example.altabib.design_system.navigation.screen.Screen
import com.example.altabib.core.USER_TYPE
import com.example.user.domain.usecases.GetUserUseCase

@Composable
fun RegistrationNavGraph(
    navController: NavHostController,
    startDestination: Screen,
    getUser: GetUserUseCase,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        composable(Screen.UserInfo.route) {
            UserInfoScreen()
        }

        composable(
            route = Screen.Auth.route,
            arguments = listOf(
                navArgument(NAME) { type = NavType.StringType },
                navArgument(CITY) { type = NavType.StringType },
                navArgument(USER_TYPE) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            AuthScreenRoot(backStackEntry)
        }

        composable(Screen.Home.route) {
            HomeScreen(getUser)
        }
    }
}
