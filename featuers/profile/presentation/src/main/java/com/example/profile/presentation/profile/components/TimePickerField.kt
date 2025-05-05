package com.example.profile.presentation.profile.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.altabib.core.models.Period

@Composable
fun TimePickerField(
    text: String,
    hourState: MutableState<String>,
    periodState: MutableState<Period>
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = hourState.value,
            onValueChange = {
                hourState.value = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            label = { Text(text) },
            modifier = Modifier.width(50.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        DropdownMenuBox(
            selected = periodState.value,
            onSelected = { periodState.value = it }
        )
    }
}
