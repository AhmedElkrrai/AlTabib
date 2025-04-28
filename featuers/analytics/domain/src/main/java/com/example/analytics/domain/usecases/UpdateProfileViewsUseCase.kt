package com.example.analytics.domain.usecases

import com.example.analytics.domain.AnalyticsRepository
import com.example.user.domain.usecases.GetUserUseCase

class UpdateProfileViewsUseCase(
    private val repository: AnalyticsRepository,
    private val getUserUseCase: GetUserUseCase
) {
    suspend operator fun invoke(doctorId: String) {
        val user = getUserUseCase()
        if (user != null) {
            repository.updateProfileViews(doctorId, user.uid)
        }
    }
}
