package com.example.altabib.featuers.dashboard.presentation.specialization

import com.example.altabib.core.domain.util.DataError

sealed class SpecializationEvent {
    data object Back : SpecializationEvent()
    data class Navigate(val route: String) : SpecializationEvent()
    data class ShowToast(val error: DataError) : SpecializationEvent()
}
