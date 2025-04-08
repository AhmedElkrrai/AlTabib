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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.altabib.featuers.user.domain.entities.UserType
import com.example.altabib.featuers.user.domain.usecases.GetUserUseCase
import com.example.altabib.navigation.utils.LocalNavController
import com.example.altabib.navigation.graph.RegistrationNavGraph
import com.example.altabib.navigation.screen.Screen
import com.example.altabib.ui.theme.AlTabibTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val getUser: GetUserUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemBars()
        setContent {
            AlTabibTheme {
                val rootNavController = rememberNavController()
                val user = getUser()
                val startDestination =
                    if (user != null) Screen.Home else Screen.UserInfo

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CompositionLocalProvider(LocalNavController provides rootNavController) {
                        RegistrationNavGraph(
                            navController = rootNavController,
                            startDestination = startDestination,
                            getUser = getUser
                        )
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
