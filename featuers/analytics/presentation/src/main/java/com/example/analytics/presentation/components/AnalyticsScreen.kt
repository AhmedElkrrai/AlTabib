package com.example.analytics.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.altabib.design.R
import com.example.altabib.design_system.components.Loading
import com.example.altabib.design_system.components.ReviewCard
import com.example.altabib.design_system.components.ViewsBarChart
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.design_system.utils.FormatCompose
import com.example.analytics.presentation.AnalyticsState
import com.example.analytics.presentation.util.processViewData
import com.example.analytics.presentation.util.viewsPerMonth

@Composable
fun AnalyticsScreen(
    state: AnalyticsState,
    modifier: Modifier = Modifier,
) {
    val viewData = processViewData(state.profile.views)
    val views = viewsPerMonth(viewData)

    val animatedRating = animateFloatAsState(
        targetValue = state.rating,
        animationSpec = tween(durationMillis = 3000),
        label = "AnimatedRating"
    )

    if (state.isLoading) {
        Loading()
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            AnalyticsHeader(
                reviews = state.reviews,
                animatedRating = animatedRating,
                views = views
            )
            Spacer(modifier = Modifier.height(16.dp))

            ViewsBarChart(
                viewData = viewData,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1.5f)
            )

            FormatCompose {
                Spacer(modifier = Modifier.height(6.dp))

                // TODO: pass cap from remote config
                if (!state.premium) {
                    Disclaimer(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        cap = 5
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = getLocalizedString(R.string.patients_reviews),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                if (state.reviewList.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = getLocalizedString(R.string.no_reviews),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        itemsIndexed(
                            state.reviewList
                                .sortedByDescending { it.createdAt })
                        { _, review ->
                            ReviewCard(review)
                        }
                    }
                }
            }
        }
    }
}
