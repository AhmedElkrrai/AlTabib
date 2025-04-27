package com.example.altabib.featuers.dashboard.presentation.specialization.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.altabib.design.R
import com.example.altabib.featuers.dashboard.presentation.doctor.components.DoctorCard
import com.example.altabib.featuers.dashboard.presentation.specialization.SpecializationAction
import com.example.altabib.featuers.dashboard.presentation.specialization.SpecializationState
import com.example.altabib.design_system.components.Loading
import com.example.altabib.design_system.components.TopAppBarWithBackButton
import com.example.altabib.design_system.components.VerticalGrid
import com.example.altabib.design_system.localization.getLocalizedString

@Composable
fun SpecializationScreen(
    specialization: String,
    state: SpecializationState,
    modifier: Modifier = Modifier,
    onAction: (SpecializationAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarWithBackButton(
                title = specialization,
                onBackClick = { onAction(SpecializationAction.OnBackClick) }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        if (state.isLoading) {
            Loading(innerPadding)
        } else if (state.doctors.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = getLocalizedString(R.string.no_doctors_available),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            VerticalGrid(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                itemsIndexed(state.doctors) { _, doctor ->
                    DoctorCard(
                        doctor = doctor,
                        onClick = {
                            onAction(SpecializationAction.OnDoctorClick(doctor))
                        }
                    )
                }
            }
        }
    }
}
