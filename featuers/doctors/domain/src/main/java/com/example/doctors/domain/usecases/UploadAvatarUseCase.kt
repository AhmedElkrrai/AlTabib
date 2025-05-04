package com.example.doctors.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.doctors.domain.DoctorRepository

class UploadAvatarUseCase(
    private val repository: DoctorRepository
) {
    suspend operator fun invoke(doctorId: String, bytes: ByteArray): Result<String, DataError> {
        return repository.uploadAvatar(doctorId, bytes)
    }
}
