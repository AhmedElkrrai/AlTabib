package com.example.analytics.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.onError
import com.example.altabib.core.onSuccess
import com.example.analytics.domain.usecases.GetProfileViewsUseCase
import com.example.doctors.domain.usecases.GetDoctorUseCase
import com.example.user.domain.usecases.GetUserUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnalyticsViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val getProfileViewsUseCase: GetProfileViewsUseCase,
    private val getDoctorUseCase: GetDoctorUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AnalyticsState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<AnalyticsEvent>()
    val event: SharedFlow<AnalyticsEvent> = _event

    fun onAction(action: AnalyticsAction) {
        when (action) {
            is AnalyticsAction.Fetch -> fetch()
            is AnalyticsAction.Navigate -> navigate(action.route)
        }
    }

    private fun fetch() {
        viewModelScope.launch {
            val doctorId = getUserUseCase.invoke()?.uid ?: return@launch
            _state.update { it.copy(isLoading = true) }
            getProfileViewsUseCase.invoke(doctorId)
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            profile = result
                        )
                    }
                    getDoctorUseCase.invoke(doctorId)
                        .onSuccess { doctor ->
                            _state.update {
                                it.copy(
                                    rating = doctor.rating,
                                    reviews = doctor.reviews,
                                    reviewList = doctor.reviewsList,
                                    isLoading = false
                                )
                            }
                        }
                        .onError {
                            _event.emit(AnalyticsEvent.ShowToast(it))
                        }
                }
                .onError {
                    _event.emit(AnalyticsEvent.ShowToast(it))
                }
        }
    }

    private fun navigate(route: String) {
        viewModelScope.launch {
            _event.emit(AnalyticsEvent.Navigate(route))
        }
    }
}
