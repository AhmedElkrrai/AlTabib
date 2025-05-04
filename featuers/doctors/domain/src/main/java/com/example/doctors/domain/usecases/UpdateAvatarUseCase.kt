package com.example.doctors.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.doctors.domain.DoctorRepository

class UpdateAvatarUseCase(
    private val repository: DoctorRepository
) {
    suspend operator fun invoke(
        uploadToServer: Boolean,
        doctorId: String,
        bytes: ByteArray,
        oldAvatar: String? = null
    ): Result<String, DataError> {
        return if (uploadToServer) {
            repository.uploadAvatar(doctorId, bytes)
        } else {
            repository.cacheAvatar(doctorId, bytes, oldAvatar)
        }
    }
}
