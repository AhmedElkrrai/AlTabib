package com.example.altabib.featuers.dashboard.domain.usecases

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.core.domain.util.Result

class GetDoctorsBySpecializationUseCase(

) {
    operator fun invoke(key: String): Result<List<Doctor>, DataError> {
        // Todo: implement logic
        return Result.Success(dummyDoctors)
    }
}
