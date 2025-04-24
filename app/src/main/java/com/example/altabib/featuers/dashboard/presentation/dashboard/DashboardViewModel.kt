package com.example.altabib.featuers.dashboard.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.altabib.core.DataError
import com.example.altabib.core.onError
import com.example.altabib.core.onSuccess
import com.example.altabib.featuers.dashboard.domain.usecases.SearchDoctorsUseCase
import com.example.altabib.featuers.dashboard.presentation.specialization.models.Specialization
import com.example.altabib.design_system.navigation.screen.PatientScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardViewModel(
    private val searchDoctorsUseCase: SearchDoctorsUseCase,
) : ViewModel() {
    private var searchJob: Job? = null
    private val _state = MutableStateFlow(DashboardState())
    val state = _state
        .onStart {
            loadSpecializations()
            observeSearchQuery()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(3000L),
            DashboardState()
        )

    private val _event = MutableSharedFlow<DashboardEvent>()
    val event: SharedFlow<DashboardEvent> = _event

    fun onAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.OnSearchQueryChanged -> {
                _state.update {
                    it.copy(
                        searchQuery = action.query
                    )
                }
            }

            is DashboardAction.OpenDoctorDetails -> {
                viewModelScope.launch {
                    _event.emit(
                        DashboardEvent.Navigate(
                            PatientScreen.DoctorDetails.createRoute(
                                doctorId = action.doctor.id
                            )
                        )
                    )
                }
            }

            is DashboardAction.OpenSpecializationScreen -> {
                viewModelScope.launch {
                    _event.emit(
                        DashboardEvent.Navigate(
                            PatientScreen.Specialization.createRoute(
                                specialization = action.specialization.key
                            )
                        )
                    )
                }
            }
        }
    }

    private fun loadSpecializations() {
        viewModelScope.launch {
            _state.update { it.copy(specializations = Specialization.entries.toList()) }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(1000L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                searchResult = emptyList()
                            )
                        }
                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchDoctors()
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun searchDoctors() = viewModelScope.launch {
        _state.update { state ->
            state.copy(
                isLoading = true,
            )
        }
        val searchResult = withContext(Dispatchers.Default) {
            searchDoctorsUseCase(_state.value.searchQuery)
        }

        searchResult
            .onSuccess { doctors ->
                if (doctors.isEmpty())
                    _event.emit(DashboardEvent.ShowToast(DataError.NoSearchResult))

                _state.update { state ->
                    state.copy(
                        searchResult = doctors,
                        isLoading = false
                    )
                }
            }
            .onError {
                _event.emit(DashboardEvent.ShowToast(it))
            }
    }
}
