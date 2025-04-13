package com.example.altabib.featuers.settings.domain.entities

data class Patient(
    val id: String = "",
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
