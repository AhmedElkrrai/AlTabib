package com.example.analytics.presentation

import com.example.altabib.core.DataError

sealed interface AnalyticsEvent {
    data class ShowToast(val error: DataError) : AnalyticsEvent
    data class ShowMessage(val msgRes: Int) : AnalyticsEvent
}
