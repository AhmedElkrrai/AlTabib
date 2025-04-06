package com.example.altabib.featuers.user.presentation.auth

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.domain.util.onError
import com.example.altabib.core.domain.util.onSuccess
import com.example.altabib.core.presentation.util.getMessage
import com.example.altabib.featuers.user.domain.GoogleSignInUseCase
import com.example.altabib.featuers.user.domain.User
import com.example.altabib.navigation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val useCase: GoogleSignInUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    private val _event = MutableSharedFlow<AuthEvent>()
    val event: SharedFlow<AuthEvent> = _event

    fun onAction(action: AuthenticationAction) {
        when (action) {
            is AuthenticationAction.OnRegister -> {
                registerUser(action.user)
            }
        }
    }

    private fun registerUser(user: User) {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            useCase
                .register(user)
                .onSuccess {
                    _state.update { it.copy(isLoading = false) }
                    _event.emit(AuthEvent.Navigate(Screen.Home.route))
                }
                .onError { error ->
                    _state.update { state ->
                        state.copy(
                            isLoading = false,
                            error = state.error
                        )
                    }

                    _event.emit(AuthEvent.ShowToast(error))
                }
        }
    }
}
