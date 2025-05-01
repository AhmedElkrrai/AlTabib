package com.example.analytics.data

import android.util.Log
import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.altabib.core.getTodayDate
import com.example.altabib.core.toInteger
import com.example.analytics.domain.AnalyticsRepository
import com.example.analytics.domain.entites.ProfileView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

private const val PROFILE_VIEWS_PATH = "profile_views"
private const val VIEWS_FIELD = "views"

class AnalyticsRepositoryImpl(
    private val firestore: FirebaseFirestore,
) : AnalyticsRepository {

    override suspend fun getProfileViews(
        doctorId: String,
    ): Result<ProfileView, DataError> {
        return try {
            val snapshot = firestore.collection(PROFILE_VIEWS_PATH)
                .document(doctorId)
                .get()
                .await()

            if (snapshot.exists()) {
                val profileView = snapshot.toObject(ProfileView::class.java)
                Result.Success(profileView!!)
            } else {
                Result.Error(DataError.FailedToRetrieveData)
            }
        } catch (e: Exception) {
            Log.e("AnalyticsRepo", "Error in getProfileViews", e)
            Result.Error(DataError.FailedToRetrieveData)
        }
    }

    override suspend fun updateProfileViews(
        doctorId: String,
        patientId: String,
        premium: Boolean
    ) {
        try {
            val docRef = firestore.collection(PROFILE_VIEWS_PATH)
                .document(doctorId)

            val today = getTodayDate() // "2025-04-29"

            firestore.runTransaction { transaction ->
                val snapshot = transaction.get(docRef)

                if (snapshot.exists()) {
                    val profileView = snapshot.toObject(ProfileView::class.java)!!

                    val viewsMap = profileView.views.toMutableMap()
                    val todayPatientIds = viewsMap[today]?.toMutableList() ?: mutableListOf()

                    if (!todayPatientIds.contains(patientId)) {
                        todayPatientIds.add(patientId)
                        viewsMap[today] = todayPatientIds
                        transaction.update(docRef, VIEWS_FIELD, viewsMap)
                    } else {
                        // Patient already viewed this doctor today, no-op
                    }
                } else {
                    // First time view for this doctor
                    val newProfileView = ProfileView(
                        doctorId = doctorId,
                        premium = premium.toInteger(),
                        views = mapOf(
                            today to listOf(patientId)
                        )
                    )
                    transaction.set(docRef, newProfileView)
                }
            }.await()
        } catch (e: Exception) {
            Log.e("AnalyticsRepo", "Error in updateProfileViews", e)
            Result.Error(DataError.FailedToUpdateData)
        }
    }
}
