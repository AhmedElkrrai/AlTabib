package com.example.altabib.featuers.dashboard.presentation.doctor.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.altabib.featuers.dashboard.domain.entities.Doctor

@Composable
fun DoctorList(
    doctors: List<Doctor>,
    onDoctorClick: (Doctor) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        itemsIndexed(doctors) { _, doctor ->
            DoctorCard(
                doctor = doctor,
                onDoctorClick = onDoctorClick
            )
        }
    }
}
