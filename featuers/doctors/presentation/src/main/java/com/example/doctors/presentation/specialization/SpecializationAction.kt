package com.example.doctors.presentation.specialization

import com.example.user.domain.entities.Doctor

sealed class SpecializationAction {
    data class LoadDoctors(val specializationKey: String) : SpecializationAction()
    data class OnDoctorClick(val doctor: Doctor) : SpecializationAction()
    data object OnBackClick : SpecializationAction()
}
