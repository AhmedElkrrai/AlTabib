package com.example.altabib.featuers.dashboard.presentation.doctor

import com.example.altabib.core.domain.util.DataError

sealed class DoctorDetailsEvent {
    data object Back : DoctorDetailsEvent()
    data class NavigateToAddress(val address: String) : DoctorDetailsEvent()
    data class Navigate(val route: String) : DoctorDetailsEvent()
    data class ShowToast(val error: DataError) : DoctorDetailsEvent()
    data class ShowMessage(val msgRes: Int) : DoctorDetailsEvent()
}
