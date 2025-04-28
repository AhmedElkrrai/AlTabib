package com.example.analytics.domain.usecases

import com.example.altabib.core.getOrDefault
import com.example.analytics.domain.AnalyticsRepository
import com.example.doctors.domain.usecases.GetDoctorByIdUseCase
import com.example.user.domain.usecases.GetUserUseCase

class UpdateProfileViewsUseCase(
    private val repository: AnalyticsRepository,
    private val getUserUseCase: GetUserUseCase,
    private val getDoctorUseCase: GetDoctorByIdUseCase
) {
    suspend operator fun invoke(doctorId: String) {
        val patient = getUserUseCase() ?: return
        val doctor = getDoctorUseCase(doctorId)
            .getOrDefault(null) ?: return

        repository.updateProfileViews(
            doctorId = doctorId,
            patientId = patient.uid,
            premium = doctor.premium
        )
    }
}
