package com.example.analytics.domain.entites

data class ProfileView(
    val doctorId: String = "",
    val premium: Int = 0, // 0 = free, 1 = premium
    val views: Map<String, List<String>> = emptyMap() // date -> list of patientIds
)


