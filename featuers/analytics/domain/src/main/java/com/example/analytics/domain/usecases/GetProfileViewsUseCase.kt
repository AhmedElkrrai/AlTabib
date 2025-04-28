package com.example.analytics.domain.usecases

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.analytics.domain.AnalyticsRepository
import com.example.analytics.domain.entites.ProfileView

class GetProfileViewsUseCase(
    private val repository: AnalyticsRepository
) {
    suspend operator fun invoke(doctorId: String): Result<ProfileView, DataError> {
        return repository.getProfileViews(doctorId)
    }
}