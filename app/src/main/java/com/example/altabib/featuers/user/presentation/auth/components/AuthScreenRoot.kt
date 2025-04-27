package com.example.altabib.featuers.user.presentation.auth.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import com.example.altabib.design_system.utils.ObserveEvents
import com.example.altabib.design_system.utils.getMessage
import com.example.user.domain.entities.User
import com.example.user.domain.entities.UserType
import com.example.altabib.featuers.user.presentation.auth.AuthEvent
import com.example.altabib.featuers.user.presentation.auth.AuthViewModel
import com.example.altabib.core.CITY
import com.example.altabib.design_system.navigation.utils.LocalNavController
import com.example.altabib.core.NAME
import com.example.altabib.design_system.navigation.screen.Screen
import com.example.altabib.core.USER_TYPE
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreenRoot(
    backStackEntry: NavBackStackEntry,
    viewModel: AuthViewModel = koinViewModel()
) {

    val context = LocalContext.current
    val state = viewModel.state.collectAsStateWithLifecycle()
    val navController = LocalNavController.current

    val name = backStackEntry.arguments?.getString(NAME) ?: ""
    val city = backStackEntry.arguments?.getString(CITY) ?: ""
    val userType = backStackEntry.arguments?.getString(USER_TYPE) ?: UserType.Patient.key

    val user = User(
        name = name,
        city = city,
        type = UserType.fromKey(userType)
    )

    ObserveEvents(events = viewModel.event) { event ->
        when (event) {
            is AuthEvent.Navigate -> navController.navigate(event.route) {
                popUpTo(Screen.UserInfo.route) { inclusive = true }
            }

            is AuthEvent.ShowToast -> {
                Toast
                    .makeText(
                        context,
                        event.error.getMessage(context),
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }
    }

    AuthScreen(
        state = state.value,
        user = user,
        onAction = viewModel::onAction
    )
}
