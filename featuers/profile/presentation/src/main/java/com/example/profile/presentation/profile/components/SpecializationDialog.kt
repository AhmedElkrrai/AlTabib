package com.example.profile.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.altabib.design.R
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.altabib.design_system.models.Specialization
import com.example.altabib.design_system.utils.FormatCompose

@Composable
fun SpecializationDialog(
    current: String,
    onDismiss: () -> Unit,
    onSelect: (String) -> Unit
) {
    FormatCompose {

        val specializations = Specialization.entries
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {},
            title = { Text(getLocalizedString(R.string.choose_specialization)) },
            text = {
                LazyColumn {
                    specializations.forEach { specialization ->
                        val isSelected = specialization.key == current
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onSelect(specialization.key) }
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = isSelected,
                                    onClick = { onSelect(specialization.key) }
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(text = getLocalizedString(specialization.nameResource))
                            }
                        }
                    }
                }
            }
        )
    }
}
