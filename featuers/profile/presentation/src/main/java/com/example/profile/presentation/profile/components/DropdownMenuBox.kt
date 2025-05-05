package com.example.profile.presentation.profile.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.altabib.core.models.Period
import com.example.altabib.design_system.utils.displayName

@Composable
fun DropdownMenuBox(
    selected: Period,
    onSelected: (Period) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(
                text = selected.displayName(),
                color = Color.White
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            Period.entries.forEach {
                DropdownMenuItem(
                    onClick = {
                        onSelected(it)
                        expanded = false
                    },
                    text = {
                        Text(
                            text = it.displayName(),
                            color = Color.White
                        )
                    }
                )
            }
        }
    }
}
