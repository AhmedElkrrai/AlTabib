package com.example.doctors.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.doctors.domain.DoctorRepository
import com.example.user.domain.entities.Doctor
import com.example.user.domain.usecases.GetUserUseCase

class SearchDoctorsUseCase(
    private val doctorRepository: DoctorRepository,
    private val getUserUseCase: GetUserUseCase,
) {
    suspend operator fun invoke(query: String): Result<List<Doctor>, DataError> {
        val user = getUserUseCase.invoke()
        val city = user?.city ?: return Result.Error(DataError.GeneralError)

        return doctorRepository.searchDoctors(city, query)
    }
}
