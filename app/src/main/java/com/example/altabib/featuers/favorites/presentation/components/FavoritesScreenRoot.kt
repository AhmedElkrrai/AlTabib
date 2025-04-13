package com.example.altabib.featuers.favorites.presentation.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.altabib.core.presentation.util.ObserveEvents
import com.example.altabib.core.presentation.util.getMessage
import com.example.altabib.featuers.favorites.presentation.FavoritesAction
import com.example.altabib.featuers.favorites.presentation.FavoritesEvent
import com.example.altabib.featuers.favorites.presentation.FavoritesViewModel
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
