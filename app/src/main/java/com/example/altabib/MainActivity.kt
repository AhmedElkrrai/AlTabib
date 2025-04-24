package com.example.altabib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.altabib.featuers.user.domain.usecases.GetUserUseCase
import com.example.altabib.navigation.graph.RegistrationNavGraph
import com.example.altabib.navigation.screen.Screen
import com.example.altabib.navigation.utils.LocalNavController
import com.example.altabib.design_system.theme.AlTabibTheme
import com.example.altabib.design_system.utils.enableStickyImmersiveMode
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val getUser: GetUserUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            enableStickyImmersiveMode()
        }
    }
}
