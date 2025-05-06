package com.example.profile.presentation.profile.actions

import com.example.altabib.design_system.navigation.screen.DoctorScreen
import com.example.profile.presentation.profile.state.ProfileEvent
import com.example.profile.presentation.profile.state.ProfileReducer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OnEditAvailabilityAction {
    operator fun invoke(): Flow<ProfileReducer> {
        return flow {
            emit(
                ProfileReducer.invoke(
                    event = ProfileEvent.Navigate(DoctorScreen.EditAvailability.route)
                )
            )
        }
    }
}