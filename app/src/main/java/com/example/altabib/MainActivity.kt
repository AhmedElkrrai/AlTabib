package com.example.altabib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.altabib.featuers.user.domain.UserType
import com.example.altabib.featuers.user.presentation.UserInfoScreen
import com.example.altabib.navigation.CITY
import com.example.altabib.navigation.NAME
import com.example.altabib.navigation.Screen
import com.example.altabib.navigation.USER_TYPE
import com.example.altabib.ui.theme.AlTabibTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlTabibTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = Screen.UserInfo.route) {
                    composable(Screen.UserInfo.route) { UserInfoScreen(navController) }
                    composable(Screen.Auth.route) { backStackEntry ->
                        val name = backStackEntry.arguments?.getString(NAME) ?: ""
                        val city = backStackEntry.arguments?.getString(CITY) ?: ""
                        val userType =
                            backStackEntry.arguments?.getString(USER_TYPE) ?: UserType.Patient.key
                        // FirebaseAuthScreen(navController, name, city, userType) // (Create this screen next)
                    }
                }
            }
        }
    }
}
