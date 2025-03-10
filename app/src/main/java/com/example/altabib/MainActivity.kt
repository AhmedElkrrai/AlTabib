package com.example.altabib

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
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
        hideSystemBars()
        setContent {
            AlTabibTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = Screen.UserInfo.route) {
                        composable(Screen.UserInfo.route) { UserInfoScreen(navController) }
                        composable(Screen.Auth.route) { backStackEntry ->
                            val name = backStackEntry.arguments?.getString(NAME) ?: ""
                            val city = backStackEntry.arguments?.getString(CITY) ?: ""
                            val userType =
                                backStackEntry.arguments?.getString(USER_TYPE)
                                    ?: UserType.Patient.key
                            // FirebaseAuthScreen(navController, name, city, userType) // (Create this screen next)
                        }
                    }
                }
            }
        }
    }

    private fun hideSystemBars() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.let { controller ->
                controller.hide(WindowInsets.Type.systemBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                    )
        }
    }
}
