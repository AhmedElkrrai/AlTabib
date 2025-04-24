package com.example.altabib.featuers.dashboard.presentation.dashboard.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.altabib.R
import com.example.altabib.featuers.dashboard.presentation.dashboard.DashboardAction
import com.example.altabib.featuers.dashboard.presentation.dashboard.DashboardState
import com.example.altabib.featuers.dashboard.presentation.doctor.components.DoctorCard
import com.example.altabib.featuers.dashboard.presentation.specialization.components.SpecializationCard
import com.example.altabib.design_system.components.Loading
import com.example.altabib.design_system.components.VerticalGrid
import com.example.altabib.design_system.localization.getLocalizedString

@Composable
fun DashboardScreen(
    state: DashboardState,
    onAction: (DashboardAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        SearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = { query ->
                onAction(DashboardAction.OnSearchQueryChanged(query))
            },
            onImeSearch = {
                // No-op: handled by debounce
            },
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedContent(
            targetState = Triple(state.searchQuery, state.isLoading, state.searchResult),
            label = "dashboard_content"
        ) { (query, isLoading, result) ->
            when {
                isLoading -> {
                    Loading()
                }

                query.isNotBlank() -> {
                    if (result.isNotEmpty()) {
                        VerticalGrid {
                            itemsIndexed(result) { _, doctor ->
                                DoctorCard(
                                    doctor = doctor,
                                    onClick = {
                                        onAction(
                                            DashboardAction.OpenDoctorDetails(doctor)
                                        )
                                    }
                                )
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = getLocalizedString(R.string.no_results),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }

                else -> {
                    VerticalGrid {
                        itemsIndexed(state.specializations) { _, specialization ->
                            SpecializationCard(
                                specialization = specialization,
                                onClick = {
                                    onAction(DashboardAction.OpenSpecializationScreen(it))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
