package com.example.altabib.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.altabib.featuers.home.presentation.HomeScreen
import com.example.altabib.featuers.user.domain.entities.UserType
import com.example.altabib.featuers.user.domain.usecases.GetUserUseCase
import com.example.altabib.featuers.user.presentation.auth.AuthScreenRoot
import com.example.altabib.featuers.user.presentation.info.UserInfoScreen
import com.example.altabib.navigation.utils.CITY
import com.example.altabib.navigation.utils.NAME
import com.example.altabib.navigation.screen.Screen
import com.example.altabib.navigation.utils.USER_TYPE

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
