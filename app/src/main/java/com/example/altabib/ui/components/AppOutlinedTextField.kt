package com.example.altabib.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.altabib.ui.theme.Gray
import com.example.altabib.ui.theme.Primary

@Composable
fun AppOutlinedTextFiled(
    name: String,
    onValueChange: (String) -> Unit,
    keyboardController: androidx.compose.ui.platform.SoftwareKeyboardController?,
) {

    OutlinedTextField(
        value = name,
        onValueChange = { onValueChange(it) },
        label = { Text(text = "Name", fontSize = 16.sp, color = Primary) },
        textStyle = TextStyle(color = Color.White),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Go
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Primary,
            unfocusedBorderColor = Gray
        ),
        keyboardActions = KeyboardActions(
            onGo = {
                keyboardController?.hide()
            }
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
