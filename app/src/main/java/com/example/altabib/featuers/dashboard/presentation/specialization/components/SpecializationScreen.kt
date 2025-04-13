package com.example.altabib.featuers.dashboard.presentation.specialization.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.altabib.R
import com.example.altabib.featuers.dashboard.presentation.doctor.components.DoctorList
import com.example.altabib.featuers.dashboard.presentation.specialization.SpecializationAction
import com.example.altabib.featuers.dashboard.presentation.specialization.SpecializationState
import com.example.altabib.ui.components.Loading
import com.example.altabib.ui.components.TopAppBarWithBackButton

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
                    text = stringResource(R.string.no_doctors_available),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            DoctorList(
                doctors = state.doctors,
                onDoctorClick = { doctor -> onAction(SpecializationAction.OnDoctorClick(doctor)) },
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }
    }
}
