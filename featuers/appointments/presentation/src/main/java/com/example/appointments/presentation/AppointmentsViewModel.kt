package com.example.appointments.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.getOrDefault
import com.example.altabib.core.onError
import com.example.altabib.core.onSuccess
import com.example.altabib.design.R
import com.example.appointments.domain.usecases.DismissAppointmentUseCase
import com.example.appointments.domain.usecases.GetAppointmentsUseCase
import com.example.appointments.presentation.mappers.toUiModel
import com.example.appointments.presentation.models.AppointmentUi
import com.example.settings.domain.usecases.GetPatientUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppointmentsViewModel(
    private val getAppointments: GetAppointmentsUseCase,
    private val dismissAppointment: DismissAppointmentUseCase,
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
            val currentList = _state.value.appointments
            if (currentList.none { it.id == appointment.id }) return@launch

            _state.value = _state.value.copy(isLoading = true)

            delay(400)

            dismissAppointment.invoke(appointment.id)
                .onSuccess {
                    val updatedList = currentList.filterNot { it.id == appointment.id }
                    _state.update {
                        it.copy(
                            appointments = updatedList,
                            isLoading = false
                        )
                    }
                }
                .onError {
                    _event.emit(AppointmentsEvent.ShowToast(it))
                    _state.value = _state.value.copy(
                        isLoading = false
                    )
                }

            _event.emit(AppointmentsEvent.ShowMessage(R.string.appointment_dismissed))
        }
    }
}
