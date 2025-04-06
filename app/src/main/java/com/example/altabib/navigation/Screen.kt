package com.example.altabib.navigation

sealed class Screen(val route: String) {
    data object UserInfo : Screen("user_info_screen")
    data object Auth : Screen("auth_screen/{$NAME}/{$CITY}/{$USER_TYPE}") {
        fun createRoute(name: String, city: String, userType: String): String {
            return "auth_screen/$name/$city/$userType"
        }
    }
    data object Home : Screen("home_screen")
}
