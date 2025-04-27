package com.example.doctors.presentation.dashboard

import com.example.altabib.core.DataError

sealed interface DashboardEvent {
    data class Navigate(val route: String) : DashboardEvent
    data class ShowToast(val error: DataError) : DashboardEvent
}
