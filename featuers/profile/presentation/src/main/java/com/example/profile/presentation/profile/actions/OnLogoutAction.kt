package com.example.profile.presentation.profile.actions

import com.example.altabib.core.DataError
import com.example.profile.presentation.profile.state.ProfileEvent
import com.example.profile.presentation.profile.state.ProfileReducer
import com.example.user.domain.usecases.LogoutUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OnLogoutAction(
    private val logoutUseCase: LogoutUseCase
) {
    operator fun invoke(): Flow<ProfileReducer> {
        return flow {
            try {
                logoutUseCase()
                emit(
                    ProfileReducer.invoke(
                        event = ProfileEvent.Logout
                    )
                )
            } catch (e: Exception) {
                emit(
                    ProfileReducer.invoke(
                        event = ProfileEvent.ShowToast(DataError.GeneralError)
                    )
                )
            }
        }
    }
}