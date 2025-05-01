package com.example.appointments.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.getOrDefault
import com.example.altabib.core.onError
import com.example.altabib.core.onSuccess
import com.example.appointments.domain.usecases.GetAppointmentsUseCase
import com.example.appointments.presentation.mappers.toUiModel
import com.example.appointments.presentation.models.AppointmentUi
import com.example.settings.domain.usecases.GetPatientUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppointmentsViewModel(
    private val getAppointments: GetAppointmentsUseCase,
    private val getPatient: GetPatientUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AppointmentsState())
    val state = _state
        .onStart {
            loadAppointments()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(3000L),
            AppointmentsState()
        )

    private val _event = MutableSharedFlow<AppointmentsEvent>()
    val event: SharedFlow<AppointmentsEvent> = _event

    fun onAction(action: AppointmentsAction) {
        when (action) {
            is AppointmentsAction.Dismiss -> dismiss(action.appointment)
        }
    }

    private fun loadAppointments() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true
            )
            getAppointments.invoke()
                .onSuccess { appointments ->
                    val appointmentUis = appointments.map { appointment ->
                        val name: String = getPatient
                            .invoke(appointment.patientId)
                            ?.getOrDefault(null)
                            ?.name ?: ""
                        appointment.toUiModel(name)
                    }
                    _state.value = _state.value.copy(
                        appointments = appointmentUis,
                        isLoading = false
                    )
                }
                .onError { error ->
                    _event.emit(AppointmentsEvent.ShowToast(error))
                    _state.value = _state.value.copy(
                        isLoading = false
                    )
                }
        }
    }

    private fun dismiss(appointment: AppointmentUi) {
        viewModelScope.launch {

        }
    }
}
