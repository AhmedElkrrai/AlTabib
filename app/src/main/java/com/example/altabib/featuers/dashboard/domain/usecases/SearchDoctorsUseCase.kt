package com.example.altabib.featuers.dashboard.domain.usecases

import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.featuers.dashboard.domain.entities.Specialization

class SearchDoctorsUseCase {
    operator fun invoke(query: String): List<Doctor> {
        // TODO: Implement search logic
        return dummyDoctors
    }
}

val dummyDoctors: List<Doctor> =
    listOf(
        Doctor(
            name = "Ahmed Eltaher",
            specialization = Specialization.DERMATOLOGY,
            rating = 9.1f,
            bio = "I am a super doctor .. I can heal you.",
            availability = "6AM - 11PM",
            inQueue = 4,
            price = 100,
            reviews = 3200
        ),

        Doctor(
            name = "Mohamed Trabies",
            specialization = Specialization.UROLOGY,
            rating = 1.5f,
            bio = "I am a loser .. I can not heal you.",
            availability = "6PM - 7PM",
            inQueue = 0,
            price = 300,
            reviews = 188
        ),

        Doctor(
            name = "Ali Aloka",
            specialization = Specialization.CARDIOLOGY,
            rating = 8.5f,
            bio = "An apple a day keeps me away",
            availability = "1PM - 9PM",
            inQueue = 300,
            price = 200,
            reviews = 2000
        ),
    )
