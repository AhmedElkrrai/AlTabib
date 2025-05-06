package com.example.profile.presentation.profile

class ProfileReducer {
    fun reduce(state: ProfileState, action: ProfileAction): ProfileState {
        return when (action) {
            is ProfileAction.OnAddressChange -> state.copy(
                doctor = state.doctor.copy(address = action.value)
            )

            is ProfileAction.OnBioChange -> state.copy(
                doctor = state.doctor.copy(bio = action.value)
            )

            is ProfileAction.OnCityChange -> state.copy(
                doctor = state.doctor.copy(city = action.value)
            )

            is ProfileAction.OnContactChange -> state.copy(
                doctor = state.doctor.copy(contact = action.value)
            )

            is ProfileAction.OnNameChange -> state.copy(
                doctor = state.doctor.copy(name = action.value)
            )

            is ProfileAction.OnPriceChange -> state.copy(
                doctor = state.doctor.copy(
                    price = action.value.toIntOrNull() ?: state.doctor.price
                )
            )

            is ProfileAction.OnSpecializationSelected -> state.copy(
                doctor = state.doctor.copy(specKey = action.specKey)
            )

            is ProfileAction.OnQueueChanged -> state.copy(
                doctor = state.doctor.copy(inQueue = action.value)
            )

            else -> state
        }
    }
}
