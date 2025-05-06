package com.example.profile.presentation.profile.actions

import com.example.altabib.core.DataError
import com.example.altabib.core.onError
import com.example.altabib.core.onSuccess
import com.example.altabib.design.R
import com.example.doctors.domain.usecases.UpdateDoctorUseCase
import com.example.profile.presentation.profile.state.ProfileEvent
import com.example.profile.presentation.profile.state.ProfileReducer
import com.example.user.domain.entities.Doctor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OnSaveAction(
    private val updateDoctorUseCase: UpdateDoctorUseCase
) {
    operator fun invoke(doctor: Doctor): Flow<ProfileReducer> {
        return flow {
            updateDoctorUseCase(doctor)
                .onSuccess {
                    emit(
                        ProfileReducer.invoke(
                            event = ProfileEvent.ShowMessage(R.string.update_profile_msg)
                        )
                    )
                }
                .onError {
                    emit(
                        ProfileReducer.invoke(
                            event = ProfileEvent.ShowToast(DataError.FailedToUpdateData)
                        )
                    )
                }
        }
    }
}