package com.example.altabib.featuers.dashboard.presentation.dashboard

import com.example.altabib.core.domain.util.DataError

sealed interface DashboardEvent {
    data class Navigate(val route: String) : DashboardEvent
    data class ShowToast(val error: DataError) : DashboardEvent
}
