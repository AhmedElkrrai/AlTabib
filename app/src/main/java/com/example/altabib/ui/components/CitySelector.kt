package com.example.altabib.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.altabib.featuers.user.domain.entities.Governorate
import com.example.altabib.ui.theme.Primary

const val SELECT_CITY = "Select City"

@Composable
fun CitySelector(
    selectedCity: String,
    onCitySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Button(
                onClick = { expanded = true },
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                modifier = Modifier
                    .width(250.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = selectedCity.ifBlank { SELECT_CITY },
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(250.dp)
                    .background(Primary, shape = RoundedCornerShape(8.dp))
                    .heightIn(max = 250.dp)
            ) {
                Governorate.entries.forEach { governorate ->
                    DropdownMenuItem(
                        text = { Text(text = governorate.capital, color = Color.White) },
                        onClick = {
                            onCitySelected(governorate.capital)
                            expanded = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
