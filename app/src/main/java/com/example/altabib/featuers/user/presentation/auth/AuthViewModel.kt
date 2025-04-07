package com.example.altabib.featuers.user.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.domain.util.onError
import com.example.altabib.core.domain.util.onSuccess
import com.example.altabib.featuers.user.domain.GoogleSignInUseCase
import com.example.altabib.featuers.user.domain.RegisterUseCase
import com.example.altabib.featuers.user.domain.User
import com.example.altabib.navigation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val googleSignInUseCase: GoogleSignInUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    private val _event = MutableSharedFlow<AuthEvent>()
    val event: SharedFlow<AuthEvent> = _event

    fun onAction(action: AuthenticationAction) {
        when (action) {
            is AuthenticationAction.OnGoogleSignIn -> {
                onGoogleSignIn(action.idToken, action.user)
            }
        }
    }

    private fun onGoogleSignIn(idToken: String, user: User) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            googleSignInUseCase.invoke(idToken)
                .onSuccess { signInResult ->
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
