package com.example.altabib.featuers.dashboard.domain.usecases

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.dashboard.domain.DoctorRepository
import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.featuers.user.domain.usecases.GetUserUseCase

class SearchDoctorsUseCase(
    private val doctorRepository: DoctorRepository,
    private val getUserUseCase: GetUserUseCase,
) {
    suspend operator fun invoke(query: String): Result<List<Doctor>, DataError> {
        val user = getUserUseCase.invoke()
        val city = user?.city ?: return Result.Error(DataError.RetrievalError("City not found"))

        return doctorRepository.searchDoctors(city, query)
    }
}
