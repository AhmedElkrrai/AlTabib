package com.example.doctors.presentation.doctor

import com.example.altabib.core.DataError

sealed class DoctorDetailsEvent {
    data object Back : DoctorDetailsEvent()
    data class NavigateToAddress(val address: String) : DoctorDetailsEvent()
    data class Navigate(val route: String) : DoctorDetailsEvent()
    data class ShowToast(val error: DataError) : DoctorDetailsEvent()
    data class ShowMessage(val msgRes: Int) : DoctorDetailsEvent()
}
