package com.example.analytics.domain

import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.analytics.domain.entites.ProfileView

interface AnalyticsRepository {
    suspend fun getProfileViews(doctorId: String): Result<ProfileView, DataError>
    suspend fun updateProfileViews(doctorId: String, patientId: String)
}
