package com.example.altabib.utils

import com.example.doctors.domain.DoctorRepository
import com.example.altabib.design_system.models.Specialization
import com.example.altabib.core.Result
import com.example.altabib.design_system.models.City
import com.example.user.domain.entities.Doctor
import com.example.user.domain.entities.Review
import kotlin.random.Random

suspend fun seedDoctors(doctorRepository: DoctorRepository) {
    val names = listOf(
        "Dr. Sara Nabil", "Dr. Karim Hassan", "Dr. Laila Samir", "Dr. Omar Ezzat",
        "Dr. Nour El-Din", "Dr. Mona Fathy", "Dr. Walid Gamal", "Dr. Dina Adel",
        "Dr. Tamer Soliman", "Dr. Heba Mostafa",
        "Dr. Youssef Ali", "Dr. Hana Magdy", "Dr. Amir Khaled", "Dr. Farida Wael",
        "Dr. Ziad Sherif", "Dr. Rania Tawfik", "Dr. Sami Nasr", "Dr. Nadia Hisham",
        "Dr. Karam Mahmoud", "Dr. Salma Raouf", "Dr. Bassem Adel", "Dr. Jasmine Fares",
        "Dr. Hazem Badr", "Dr. Layla Karim", "Dr. Rafik Samy", "Dr. Ghada Nasser",
        "Dr. Ayman Lotfy", "Dr. Inas Sabry", "Dr. Tarek Fouad", "Dr. Mai Hani",
        "Dr. Hossam Rashad", "Dr. Yara Medhat", "Dr. Adel Samir", "Dr. Reem Ashraf",
        "Dr. Khaled Said", "Dr. Dalia Kamal", "Dr. Fadi Rizk", "Dr. Lamia Sobhy",
        "Dr. Osman Yasser", "Dr. Shireen Moataz"
    )

    val cities =
        listOf(City.Damanhur.name)

    val bios = listOf(
        "Passionate about patient care and innovation.",
        "Committed to excellence in medical practice.",
        "Friendly and experienced professional.",
        "Trusted by thousands of patients.",
        "Leading specialist in the region.",
        "Always learning, always caring.",
        "Here to support your health journey.",
        "Holistic approach to healing.",
        "Loves complex cases and big smiles.",
        "Caring beyond prescriptions."
    )

    val availabilities = listOf(
        "9AM - 5PM", "10AM - 6PM", "1PM - 9PM", "4PM - 11PM", "8AM - 2PM"
    )

    val addresses = listOf(
        "Dokki, Giza", "Zamalek, Cairo", "Smouha, Alexandria", "Sidi Gaber, Alexandria",
        "Nasr City, Cairo", "Maadi, Cairo", "Downtown, Tanta", "Talkha, Mansoura"
    )

    val reviewList = listOf(
        Review(
            id = "review_1",
            userName = "John Doe",
            text = "Great doctor, highly recommended!",
            rating = 5
        ),
        Review(
            id = "review_2",
            userName = "Jane Smith",
            text = "I had a wonderful experience with this doctor.",
            rating = 4
        ),
        Review(
            id = "review_3",
            userName = "Bob Johnson",
            text = "The doctor was knowledgeable and helpful.",
            rating = 4
        ),
        Review(
            id = "review_4",
            userName = "Alice Brown",
            text = "I couldn't be happier with the treatment.",
            rating = 5
        ),

        Review(
            id = "review_5",
            userName = "Emily Davis",
            text = "The doctor's attention to detail was impressive.",
            rating = 4

        )
    )

    val dummyDoctors = (0 until 20).map { index ->
        Doctor(
            id = "doc_${index + 1}",
            name = names[index % names.size],
            specKey = Specialization.entries.random().key,
            city = cities.random(),
            rating = Random
                .nextDouble(2.5, 5.0)
                .toFloat(),
            reviews = Random.nextInt(50, 1000),
            bio = bios[index % bios.size],
            availability = availabilities.random(),
            inQueue = Random.nextInt(0, 20),
            price = Random.nextInt(100, 500),
            premium = Random.nextBoolean(),
            address = addresses.random(),
            reviewsList = reviewList
        )
    }

    for (doctor in dummyDoctors) {
        val result = doctorRepository.addDoctor(doctor)
        if (result is Result.Error) {
            println("Failed to add ${doctor.name}: ${result.error}")
        } else {
            println("Added ${doctor.name} in ${doctor.city} (${doctor.specKey})")
        }
    }
}
