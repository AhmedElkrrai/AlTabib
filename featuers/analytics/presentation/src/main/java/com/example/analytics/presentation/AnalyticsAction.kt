package com.example.analytics.presentation

sealed interface AnalyticsAction {
    data class Navigate(val route: String) : AnalyticsAction
    data object Fetch : AnalyticsAction
}