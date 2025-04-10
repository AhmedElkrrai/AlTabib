package com.example.altabib.featuers.dashboard.domain.usecases

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.dashboard.domain.entities.Doctor

class GetDoctorByIdUseCase(

) {
    operator fun invoke(doctorId: String): Result<Doctor, DataError> {
        // TODO: Implement the use case logic to retrieve a doctor by their ID
        return Result.Success(dummyDoctors.last())
    }
}
