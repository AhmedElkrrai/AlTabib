package com.example.profile.presentation.availability.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.altabib.core.models.DayOfWeek
import com.example.altabib.design.R
import com.example.altabib.design_system.components.AppOutlinedButton
import com.example.altabib.design_system.components.Loading
import com.example.altabib.design_system.components.TopAppBarWithBackButton
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.design_system.utils.ForceImmersiveMode
import com.example.altabib.design_system.utils.FormatCompose
import com.example.altabib.design_system.utils.displayName
import com.example.profile.presentation.availability.AvailabilityAction
import com.example.profile.presentation.availability.AvailabilityState
import com.example.profile.presentation.profile.components.TimeWindowEditor
import com.example.user.domain.entities.Availability
import com.example.user.domain.entities.TimeWindow

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EditAvailabilityScreen(
    modifier: Modifier = Modifier,
    state: AvailabilityState,
    onAction: (AvailabilityAction) -> Unit
) {
    ForceImmersiveMode()

    val selectedDays = state.data.days
    val timeWindows = state.data.hours

    if (state.isLoading) {
        Loading()
    } else {
        Scaffold(
            topBar = {
                TopAppBarWithBackButton(
                    title = getLocalizedString(R.string.edit_availability),
                    onBackClick = { onAction(AvailabilityAction.Back) }
                )
            },
        ) { innerPadding ->
            FormatCompose {
                LazyColumn(
                    modifier = modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Text(
                            text = getLocalizedString(R.string.select_days),
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            DayOfWeek.entries.forEach { day ->
                                val isSelected = day in selectedDays
                                val updatedDays = selectedDays.toMutableList().apply {
                                    if (isSelected) remove(day) else add(day)
                                }

                                FilterChip(
                                    selected = isSelected,
                                    onClick = {
                                        onAction(
                                            AvailabilityAction.OnAvailabilityChange(
                                                state.data.copy(
                                                    days = updatedDays
                                                )
                                            )
                                        )
                                    },
                                    label = { Text(day.displayName()) }
                                )
                            }
                        }
                    }

                    item {
                        HorizontalDivider()
                    }

                    item {
                        Text(
                            getLocalizedString(R.string.time_windows),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    itemsIndexed(timeWindows) { index, window ->
                        TimeWindowEditor(
                            window = window,
                            onChange = { updatedWindow ->
                                val updatedList = timeWindows.toMutableList().apply {
                                    set(index, updatedWindow)
                                }
                                onAction(
                                    AvailabilityAction.OnAvailabilityChange(
                                        state.data.copy(hours = updatedList)
                                    )
                                )
                            },
                            onDelete = {
                                val updatedList = timeWindows.toMutableList().apply {
                                    removeAt(index)
                                }
                                onAction(
                                    AvailabilityAction.OnAvailabilityChange(
                                        state.data.copy(hours = updatedList)
                                    )
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            AppOutlinedButton(
                                text = getLocalizedString(R.string.add_time_window),
                                onClick = {
                                    if (timeWindows.size >= 3) {
                                        onAction(
                                            AvailabilityAction.OnAddTimeWindowMaxed
                                        )
                                    } else {
                                        val updatedList = timeWindows.toMutableList().apply {
                                            add(TimeWindow())
                                        }
                                        onAction(
                                            AvailabilityAction
                                                .OnAvailabilityChange(
                                                    state.data.copy(hours = updatedList)
                                                )
                                        )
                                    }
                                }
                            )
                        }
                    }

                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            AppOutlinedButton(
                                text = getLocalizedString(R.string.save),
                                onClick = {
                                    onAction(
                                        AvailabilityAction.Save(
                                            Availability(
                                                days = selectedDays,
                                                hours = timeWindows
                                            )
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
