package com.example.profile.presentation.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.DataError
import com.example.altabib.core.LocalImageStorage
import com.example.altabib.core.onError
import com.example.altabib.core.onSuccess
import com.example.altabib.design.R
import com.example.altabib.design_system.navigation.screen.DoctorScreen
import com.example.altabib.design_system.utils.ByteArrayConverter
import com.example.doctors.domain.usecases.GetDoctorUseCase
import com.example.doctors.domain.usecases.UpdateAvatarUseCase
import com.example.doctors.domain.usecases.UpdateDoctorUseCase
import com.example.user.domain.entities.Doctor
import com.example.user.domain.usecases.GetUserUseCase
import com.example.user.domain.usecases.LogoutUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileEventHandler(
    private val getUserUseCase: GetUserUseCase,
    private val getDoctorUseCase: GetDoctorUseCase,
    private val updateDoctorUseCase: UpdateDoctorUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val updateAvatarUseCase: UpdateAvatarUseCase,
    private val byteArrayConverter: ByteArrayConverter,
    private val imageStorage: LocalImageStorage
) {
    suspend fun initDoctorData(
        updateState: (Doctor) -> Unit,
        emitEvent: suspend (ProfileEvent) -> Unit
    ) {
        val doctorId = getUserUseCase()?.uid ?: return
        getDoctorUseCase(doctorId)
            .onSuccess { doctor ->
                updateState(
                    doctor.copy(avatar = imageStorage.getCachedAvatarPath())
                )
            }
            .onError {
                emitEvent(ProfileEvent.ShowToast(DataError.FailedToRetrieveData))
            }
    }

    suspend fun handleAvatarSelected(
        uri: Uri,
        currentDoctor: Doctor,
        emitEvent: suspend (ProfileEvent) -> Unit,
        updateState: (String) -> Unit
    ) {
        val user = getUserUseCase() ?: return

        val bytes = byteArrayConverter.uriToBytes(uri)
        if (bytes == null) {
            emitEvent(ProfileEvent.ShowToast(DataError.GeneralError))
            return
        }

        updateAvatarUseCase(
            uploadToServer = false,
            doctorId = user.uid,
            bytes = bytes,
            oldAvatar = currentDoctor.avatar
        ).onSuccess { url ->
            updateState(url)
        }.onError {
            emitEvent(ProfileEvent.ShowToast(DataError.FailedToUpdateData))
        }
    }

    suspend fun saveProfile(
        doctor: Doctor,
        emitEvent: suspend (ProfileEvent) -> Unit
    ) {
        updateDoctorUseCase(doctor)
            .onSuccess {
                emitEvent(ProfileEvent.ShowMessage(R.string.update_profile_msg))
            }
            .onError {
                emitEvent(ProfileEvent.ShowToast(DataError.FailedToUpdateData))
            }
    }

    suspend fun logout(emitEvent: suspend (ProfileEvent) -> Unit) {
        try {
            logoutUseCase()
            emitEvent(ProfileEvent.Logout)
        } catch (e: Exception) {
            emitEvent(ProfileEvent.ShowToast(DataError.GeneralError))
        }
    }
}
