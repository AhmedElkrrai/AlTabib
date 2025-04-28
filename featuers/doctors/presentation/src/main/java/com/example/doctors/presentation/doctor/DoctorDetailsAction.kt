package com.example.doctors.presentation.doctor

sealed class DoctorDetailsAction {
    data class LoadDoctor(val doctorId: String) : DoctorDetailsAction()
    data object OnBackClick : DoctorDetailsAction()
    data class OnBookAppointmentClick(val doctorId: String) : DoctorDetailsAction()
    data object OnAddToFavoritesClick : DoctorDetailsAction()
    data class OnAddressClick(val address: String) : DoctorDetailsAction()
    data class UpdateProfileViews(val doctorId: String) : DoctorDetailsAction()
}
