package com.example.profile.presentation.profile.actions

import com.example.profile.presentation.profile.state.ProfileReducer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OnAddressChangeAction {
    operator fun invoke(address: String): Flow<ProfileReducer> {
        return flow {
            emit(
                ProfileReducer.invoke(
                    reducer = { state ->
                        state.copy(
                            doctor = state.doctor.copy(
                                address = address
                            )
                        )
                    }
                )
            )
        }
    }
}
