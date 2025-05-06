package com.example.profile.presentation.profile.actions

import android.net.Uri
import com.example.altabib.core.DataError
import com.example.altabib.core.onError
import com.example.altabib.core.onSuccess
import com.example.altabib.design_system.utils.ByteArrayConverter
import com.example.doctors.domain.usecases.UpdateAvatarUseCase
import com.example.profile.presentation.profile.state.ProfileEvent
import com.example.profile.presentation.profile.state.ProfileReducer
import com.example.user.domain.entities.Doctor
import com.example.user.domain.usecases.GetUserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OnAvatarSelected(
    private val getUserUseCase: GetUserUseCase,
    private val updateAvatarUseCase: UpdateAvatarUseCase,
    private val byteArrayConverter: ByteArrayConverter,
) {
    operator fun invoke(
        uri: Uri,
        currentDoctor: Doctor,
    ): Flow<ProfileReducer> {
        return flow {
            val user = getUserUseCase() ?: return@flow

            val bytes = byteArrayConverter.uriToBytes(uri)
            if (bytes == null) {
                emit(
                    ProfileReducer.invoke(
                        event = ProfileEvent.ShowToast(DataError.GeneralError)
                    )
                )
                return@flow
            }

            updateAvatarUseCase(
                uploadToServer = false,
                doctorId = user.uid,
                bytes = bytes,
                oldAvatar = currentDoctor.avatar
            ).onSuccess { url ->
                emit(
                    ProfileReducer.invoke(
                        reducer = { state ->
                            state.copy(
                                doctor = state.doctor.copy(
                                    avatar = url
                                )
                            )
                        }
                    )
                )
            }.onError {
                emit(
                    ProfileReducer.invoke(
                        event = ProfileEvent.ShowToast(DataError.FailedToUpdateData)
                    )
                )
            }
        }
    }
}