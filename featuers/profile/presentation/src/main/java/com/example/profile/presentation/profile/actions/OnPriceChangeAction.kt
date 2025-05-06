package com.example.profile.presentation.profile.actions

import com.example.profile.presentation.profile.state.ProfileReducer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OnPriceChangeAction {
    operator fun invoke(price: String): Flow<ProfileReducer> {
        return flow {
            emit(
                ProfileReducer.invoke(
                    reducer = { state ->
                        state.copy(
                            doctor = state.doctor.copy(
                                price = price.toIntOrNull() ?: state.doctor.price
                            )
                        )
                    }
                )
            )
        }
    }
}