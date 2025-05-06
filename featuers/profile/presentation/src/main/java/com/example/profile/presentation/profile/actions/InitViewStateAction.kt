package com.example.profile.presentation.profile.actions

import com.example.altabib.core.DataError
import com.example.altabib.core.LocalImageStorage
import com.example.altabib.core.onError
import com.example.altabib.core.onSuccess
import com.example.doctors.domain.usecases.GetDoctorUseCase
import com.example.profile.presentation.profile.state.ProfileEvent
import com.example.profile.presentation.profile.state.ProfileReducer
import com.example.user.domain.usecases.GetUserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InitViewStateAction(
    private val getUserUseCase: GetUserUseCase,
    private val getDoctorUseCase: GetDoctorUseCase,
    private val imageStorage: LocalImageStorage
) {
    operator fun invoke(): Flow<ProfileReducer> {
        return flow {
            val doctorId = getUserUseCase()?.uid ?: return@flow
            emit(
                ProfileReducer.invoke(
                    reducer = { state -> state.copy(isLoading = true) }
                )
            )
            getDoctorUseCase(doctorId)
                .onSuccess { doctor ->
                    emit(
                        ProfileReducer.invoke(
                            reducer = { state ->
                                state.copy(
                                    isLoading = false,
                                    doctor = doctor
                                        .copy(avatar = imageStorage.getCachedAvatarPath())
                                )
                            }
                        )
                    )
                }
                .onError {
                    emit(
                        ProfileReducer.invoke(
                            reducer = { state -> state.copy(isLoading = false) },
                            event = ProfileEvent.ShowToast(DataError.FailedToRetrieveData)
                        )
                    )
                }
        }
    }
}