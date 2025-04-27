package com.example.doctors.presentation.specialization

import com.example.altabib.core.DataError

sealed class SpecializationEvent {
    data object Back : SpecializationEvent()
    data class Navigate(val route: String) : SpecializationEvent()
    data class ShowToast(val error: DataError) : SpecializationEvent()
}
