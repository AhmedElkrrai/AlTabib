package com.example.profile.presentation

import com.example.altabib.core.DataError

sealed interface ProfileEvent {
    data class ShowToast(val error: DataError) : ProfileEvent
    data class ShowMessage(val msgRes: Int) : ProfileEvent
}
