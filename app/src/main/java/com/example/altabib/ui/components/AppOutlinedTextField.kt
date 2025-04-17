package com.example.altabib.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import com.example.altabib.ui.theme.Gray
import com.example.altabib.ui.theme.Primary

@Composable
fun AppOutlinedTextFiled(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    keyboardController: SoftwareKeyboardController?,
    initialCursorPosition: Int? = null,
    cursorColor: Color = Color.White
) {

    var textFieldValueState by remember {
        mutableStateOf(TextFieldValue(text = value))
    }
    var textFieldValue = textFieldValueState.copy(text = value)

    LaunchedEffect(key1 = initialCursorPosition, key2 = value) {
        if (initialCursorPosition != null) {
            textFieldValue = textFieldValue.copy(
                selection = TextRange(initialCursorPosition)
            )
            textFieldValueState = textFieldValue
        }
    }

    OutlinedTextField(
        value = textFieldValue,
        singleLine = singleLine,
        maxLines = maxLines,
        onValueChange = {
            textFieldValueState = it
            onValueChange(it.text)
        },
        label = { Text(text = label, fontSize = 16.sp, color = Primary) },
        textStyle = TextStyle(color = Color.White),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Go
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Primary,
            unfocusedBorderColor = Gray,
            cursorColor = cursorColor
        ),
        keyboardActions = KeyboardActions(
            onGo = {
                keyboardController?.hide()
            }
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
