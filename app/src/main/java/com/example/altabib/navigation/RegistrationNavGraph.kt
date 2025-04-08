package com.example.altabib.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.altabib.featuers.home.presentation.HomeScreen
import com.example.altabib.featuers.user.presentation.auth.AuthScreenRoot
import com.example.altabib.featuers.user.presentation.info.UserInfoScreen

@Composable
fun RegistrationNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.UserInfo.route
    ) {
        composable(Screen.UserInfo.route) {
            UserInfoScreen(navController)
        }

        composable(
            route = Screen.Auth.route,
            arguments = listOf(
                navArgument(NAME) { type = NavType.StringType },
                navArgument(CITY) { type = NavType.StringType },
                navArgument(USER_TYPE) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            AuthScreenRoot(backStackEntry, navController)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
    }
}
