package com.example.favorites.presentation.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.altabib.design_system.utils.ObserveEvents
import com.example.altabib.design_system.utils.getMessage
import com.example.favorites.presentation.FavoritesAction
import com.example.favorites.presentation.FavoritesEvent
import com.example.favorites.presentation.FavoritesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesScreenRoot(
    navController: NavHostController,
    viewModel: FavoritesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.onAction(FavoritesAction.LoadFavorites)
    }

    ObserveEvents(viewModel.event) { event ->
        when (event) {
            is FavoritesEvent.Navigate -> {
                navController.navigate(event.route)
            }

            is FavoritesEvent.ShowToast -> {
                Toast.makeText(context, event.error.getMessage(context), Toast.LENGTH_SHORT).show()
            }
        }
    }

    FavoritesScreen(
        state = state,
        onAction = viewModel::onAction
    )
}
