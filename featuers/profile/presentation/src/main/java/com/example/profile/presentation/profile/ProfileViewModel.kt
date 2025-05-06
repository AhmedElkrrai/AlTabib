package com.example.profile.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.profile.presentation.profile.actions.ProfileAction
import com.example.profile.presentation.profile.actions.ProfileActionTransformer
import com.example.profile.presentation.profile.state.ProfileEvent
import com.example.profile.presentation.profile.state.ProfileReducer
import com.example.profile.presentation.profile.state.ProfileState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(
    private val ioContext: CoroutineContext,
    private val defaultContext: CoroutineContext,
    private val transformer: ProfileActionTransformer,
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<ProfileEvent>()
    val event = _event.asSharedFlow()

    private val _action = Channel<ProfileAction>()

    init {
        consumeActions()
    }

    fun sendAction(action: ProfileAction) {
        viewModelScope.launch {
            _action.send(action)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun consumeActions() {
        viewModelScope.launch {
            _action.receiveAsFlow()
                .flatMapConcat { action ->
                    transformer.transform(action)
                }
                .flowOn(ioContext)
                .cancellable()
                .collect { reducer ->
                    launch(defaultContext) { updateViewState(reducer) }
                    reducer.event()?.let { _event.emit(it) }
                }
        }
    }

    private fun updateViewState(reducer: ProfileReducer) {
        _state.getAndUpdate { reducer.reduce(it) }
    }
}