package com.example.profile.presentation.availability.components

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
import com.example.altabib.design_system.utils.FormatCompose
import com.example.altabib.design_system.utils.displayName
import com.example.profile.presentation.availability.AvailabilityAction
import com.example.profile.presentation.availability.AvailabilityState
import com.example.profile.presentation.profile.components.TimeWindowEditor
import com.example.user.domain.entities.Availability

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EditAvailabilityScreen(
    modifier: Modifier = Modifier,
    state: AvailabilityState,
    onAction: (AvailabilityAction) -> Unit
) {
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
            }
        ) { innerPadding ->
            FormatCompose {
                Column(
                    modifier = modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    LazyColumn {
                        item {
                            Text(
                                getLocalizedString(R.string.select_days),
                                style = MaterialTheme.typography.titleMedium
                            )
                            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                DayOfWeek.entries.forEach { day ->
                                    val isSelected = day in selectedDays
                                    val days = selectedDays.toMutableList()
                                    if (isSelected) days.remove(day) else days.add(day)
                                    FilterChip(
                                        selected = isSelected,
                                        onClick = {
                                            onAction(
                                                AvailabilityAction.OnAvailabilityChange(
                                                    state.data.copy(
                                                        days = days
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
                                onChange = {
                                    //  timeWindows[index] = it
                                },
                                onDelete = {
                                    // timeWindows.removeAt(index)
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
                                        //  timeWindows.add(TimeWindow())
                                    }
                                )
                            }
                        }
                    }

                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.BottomCenter
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
