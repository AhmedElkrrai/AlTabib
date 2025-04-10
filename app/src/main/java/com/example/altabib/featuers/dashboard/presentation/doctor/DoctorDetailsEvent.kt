package com.example.altabib.featuers.dashboard.presentation.doctor

import com.example.altabib.core.domain.util.DataError

sealed class DoctorDetailsEvent {
    data object Back : DoctorDetailsEvent()
    data class ShowToast(val error: DataError) : DoctorDetailsEvent()
}
