package com.example.analytics.presentation.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.design_system.utils.ObserveEvents
import com.example.altabib.design_system.utils.getMessage
import com.example.analytics.presentation.AnalyticsAction
import com.example.analytics.presentation.AnalyticsEvent
import com.example.analytics.presentation.AnalyticsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AnalyticsScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: AnalyticsViewModel = koinViewModel()
) {

    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onAction(AnalyticsAction.Fetch)
    }

    ObserveEvents(events = viewModel.event) { event ->
        when (event) {
            is AnalyticsEvent.ShowToast -> {
                Toast
                    .makeText(context, event.error.getMessage(context), Toast.LENGTH_SHORT)
                    .show()
            }

            is AnalyticsEvent.ShowMessage -> {
                val msg = getLocalizedString(event.msgRes)
                Toast
                    .makeText(context, msg, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    AnalyticsScreen(
        state = state,
        modifier = modifier
    )
}
