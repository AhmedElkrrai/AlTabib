package com.example.altabib.featuers.dashboard.presentation.specialization

import com.example.altabib.featuers.dashboard.domain.entities.Doctor

sealed class SpecializationAction {
    data class LoadDoctors(val specializationKey: String) : SpecializationAction()
    data class OnDoctorClick(val doctor: Doctor) : SpecializationAction()
    data object OnBackClick : SpecializationAction()
}
