package com.example.analytics.domain.entites

data class ProfileView(
    val doctorId: String = "",
    val views: Map<String, List<String>> = emptyMap() // date -> list of patientIds
)
