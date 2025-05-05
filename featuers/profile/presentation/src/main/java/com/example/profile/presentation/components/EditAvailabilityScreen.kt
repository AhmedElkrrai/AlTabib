package com.example.profile.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.altabib.core.models.DayOfWeek
import com.example.altabib.design.R
import com.example.altabib.design_system.components.AppOutlinedButton
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.design_system.utils.FormatCompose
import com.example.altabib.design_system.utils.displayName
import com.example.user.domain.entities.Availability
import com.example.user.domain.entities.TimeWindow

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EditAvailabilityScreen(
    availability: Availability,
    onDismiss: () -> Unit,
    onSave: (Availability) -> Unit
) {
    val selectedDays =
        remember { mutableStateListOf<DayOfWeek>().apply { addAll(availability.days) } }
    val timeWindows =
        remember { mutableStateListOf<TimeWindow>().apply { addAll(availability.hours) } }

    FormatCompose {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                AppOutlinedButton(
                    text = getLocalizedString(R.string.save),
                    onClick = {
                        onSave(
                            Availability(
                                days = selectedDays.toList(),
                                hours = timeWindows.toList()
                            )
                        )
                        onDismiss()
                    }
                )
                Spacer(modifier = Modifier.width(16.dp))
            },
            dismissButton = {
                AppOutlinedButton(
                    text = getLocalizedString(R.string.cancel),
                    onClick = {
                        onDismiss()
                    }
                )
            },
            title = { Text(getLocalizedString(R.string.edit_availability)) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(getLocalizedString(R.string.select_days))
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        DayOfWeek.entries.forEach { day ->
                            val isSelected = day in selectedDays
                            FilterChip(
                                selected = isSelected,
                                onClick = {
                                    if (isSelected) selectedDays.remove(day)
                                    else selectedDays.add(day)
                                },
                                label = {
                                    Text(day.displayName())
                                }
                            )
                        }
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    Text(getLocalizedString(R.string.time_windows))

                    timeWindows.forEachIndexed { index, window ->
                        TimeWindowEditor(
                            window = window,
                            onChange = { timeWindows[index] = it },
                            onDelete = { timeWindows.removeAt(index) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        AppOutlinedButton(
                            text = getLocalizedString(R.string.add_time_window),
                            onClick = {
                                timeWindows.add(TimeWindow())
                            }
                        )
                    }
                }
            }
        )
    }
}
