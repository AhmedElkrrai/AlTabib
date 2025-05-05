package com.example.profile.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.altabib.core.models.AvailableHour
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.user.domain.entities.TimeWindow
import com.example.altabib.design.R
import com.example.altabib.design_system.theme.Red

@Composable
fun TimeWindowEditor(
    window: TimeWindow,
    onChange: (TimeWindow) -> Unit,
    onDelete: () -> Unit
) {
    val startTime = remember { mutableStateOf(window.start.time.toString()) }
    val startPeriod = remember { mutableStateOf(window.start.period) }
    val endTime = remember { mutableStateOf(window.end.time.toString()) }
    val endPeriod = remember { mutableStateOf(window.end.period) }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TimePickerField(
                text = getLocalizedString(R.string.from),
                hourState = startTime,
                periodState = startPeriod
            )
            Spacer(modifier = Modifier.width(8.dp))
            TimePickerField(
                text = getLocalizedString(R.string.to),
                hourState = endTime,
                periodState = endPeriod
            )
            Icon(
                Icons.Default.Delete,
                contentDescription = "Remove",
                tint = Red,
                modifier = Modifier
                    .width(24.dp)
                    .clickable { onDelete() },
            )
        }
    }

    LaunchedEffect(startTime.value, startPeriod.value, endTime.value, endPeriod.value) {
        val startHour = startTime.value.toIntOrNull() ?: 1
        val endHour = endTime.value.toIntOrNull() ?: 1

        onChange(
            TimeWindow(
                start = AvailableHour(startHour.coerceIn(1, 12), startPeriod.value),
                end = AvailableHour(endHour.coerceIn(1, 12), endPeriod.value)
            )
        )
    }
}
