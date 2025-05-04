package com.example.doctors.presentation.doctor.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.altabib.design.R
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.doctors.presentation.doctor.util.displayName
import com.example.doctors.presentation.doctor.util.formatAvailableHour
import com.example.user.domain.entities.Availability

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AvailabilityChips(availability: Availability?) {
    if (availability == null || availability.days.isEmpty() || availability.hours.isEmpty()) {
        Text(
            text = getLocalizedString(R.string.not_available),
            style = MaterialTheme.typography.bodySmall,
            color = Color.LightGray
        )
        return
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Day chips
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            availability.days.forEach { day ->
                AssistChip(
                    onClick = {},
                    label = {
                        Text(
                            text = day.displayName(),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                )
            }
        }

        // Hour chips
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            availability.hours.forEach { window ->
                val label =
                    "${formatAvailableHour(window.start)} - ${formatAvailableHour(window.end)}"
                AssistChip(
                    onClick = {},
                    label = {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                )
            }
        }
    }
}
