package com.example.user.domain.entities

data class Patient(
    val uid: String = "",
    val name: String = "",
    val city: String = "",
    val ratings: List<Rating> = emptyList()
) {
    data class Rating(
        val doctorId: String = "",
        val rating: Int = 0,
        val timestamp: Long = System.currentTimeMillis()
    )
}
