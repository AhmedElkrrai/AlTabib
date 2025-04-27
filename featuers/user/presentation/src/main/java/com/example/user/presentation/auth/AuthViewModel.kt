package com.example.user.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.onError
import com.example.altabib.core.onSuccess
import com.example.altabib.design_system.navigation.screen.Screen
import com.example.user.domain.entities.User
import com.example.user.domain.usecases.CacheUserUseCase
import com.example.user.domain.usecases.GoogleSignInUseCase
import com.example.user.domain.usecases.RegisterUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val googleSignInUseCase: GoogleSignInUseCase,
    private val registerUseCase: RegisterUseCase,
    private val cacheUserUseCase: CacheUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    private val _event = MutableSharedFlow<AuthEvent>()
    val event: SharedFlow<AuthEvent> = _event

    fun onAction(action: AuthAction) {
        when (action) {
            is AuthAction.OnGoogleSignIn -> {
                onGoogleSignIn(action.idToken, action.user)
            }
        }
    }

    private fun onGoogleSignIn(idToken: String, user: User) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            googleSignInUseCase.invoke(idToken)
                .onSuccess { signInResult ->
                    cacheUserUseCase.invoke(user.copy(uid = signInResult.uid))
                    registerUseCase.invoke(user.copy(uid = signInResult.uid))
                        .onSuccess {
                            _state.update { it.copy(isLoading = false) }
                            _event.emit(AuthEvent.Navigate(Screen.Home.route))
                        }
                        .onError {
                            _state.update { state ->
                                state.copy(isLoading = false, error = state.error)
                            }
                            _event.emit(AuthEvent.ShowToast(it))
                        }
                }
                .onError {
                    _state.update {
                        it.copy(isLoading = false, error = it.error)
                    }
                    _event.emit(AuthEvent.ShowToast(it))
                }
        }
    }
}
