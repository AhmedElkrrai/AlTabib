package com.example.altabib.featuers.dashboard.presentation.doctor

sealed class DoctorDetailsAction {
    data class LoadDoctor(val doctorId: String) : DoctorDetailsAction()
    data object OnBackClick : DoctorDetailsAction()
    data object OnBookAppointmentClick : DoctorDetailsAction()
    data object OnAddToFavoritesClick : DoctorDetailsAction()
    data class OnAddressClick(val address: String) : DoctorDetailsAction()
}
