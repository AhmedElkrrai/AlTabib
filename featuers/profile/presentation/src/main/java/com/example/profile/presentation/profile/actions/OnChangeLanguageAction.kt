package com.example.profile.presentation.profile.actions

import com.example.profile.presentation.profile.state.ProfileEvent
import com.example.profile.presentation.profile.state.ProfileReducer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OnChangeLanguageAction {
    operator fun invoke(): Flow<ProfileReducer> {
        return flow {
            emit(
                ProfileReducer.invoke(
                    event = ProfileEvent.LanguageChanged
                )
            )
        }
    }
}