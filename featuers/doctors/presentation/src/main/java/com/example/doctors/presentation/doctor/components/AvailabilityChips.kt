package com.example.doctors.presentation.doctor.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.altabib.design.R
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.design_system.utils.displayName
import com.example.altabib.design_system.utils.formatAvailableHour
import com.example.user.domain.entities.Availability

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AvailabilityChips(
    modifier: Modifier = Modifier,
    availability: Availability?
) {
    if (availability == null || availability.days.isEmpty() || availability.hours.isEmpty()) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = getLocalizedString(R.string.not_available),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = Color.LightGray
            )
        }
        return
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Day chips
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.horizontalScroll(rememberScrollState())
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
