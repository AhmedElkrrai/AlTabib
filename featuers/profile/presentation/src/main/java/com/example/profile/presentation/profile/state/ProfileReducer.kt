package com.example.profile.presentation.profile.state

sealed class ProfileReducer {
    open fun reduce(state: ProfileState): ProfileState = state
    open fun event(): ProfileEvent? = null

    private data class Reducer(
        val event: ProfileEvent? = null,
        val reducer: (ProfileState) -> ProfileState = { it },
    ) : ProfileReducer() {
        override fun reduce(state: ProfileState): ProfileState =
            reducer.invoke(state)

        override fun event(): ProfileEvent? = event
    }

    companion object {
        operator fun invoke(
            event: ProfileEvent? = null,
            reducer: (ProfileState) -> ProfileState = { it },
        ): ProfileReducer = Reducer(event, reducer)
    }
}
