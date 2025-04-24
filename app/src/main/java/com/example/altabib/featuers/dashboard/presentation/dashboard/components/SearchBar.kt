package com.example.altabib.featuers.dashboard.presentation.dashboard.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.altabib.R
import com.example.altabib.design_system.theme.DarkGray
import com.example.altabib.design_system.utils.FormatCompose
import com.example.altabib.design_system.localization.getLocalizedString

@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onImeSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    FormatCompose {
        OutlinedTextField(
            modifier = modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(12)),
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            shape = RoundedCornerShape(12),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = DarkGray,
                focusedBorderColor = DarkGray,
                unfocusedBorderColor = DarkGray,
                focusedTextColor = Black,
                unfocusedTextColor = DarkGray,
                unfocusedContainerColor = Color(0xFFF7F7F7),
                focusedContainerColor = Color.White
            ),
            textStyle = TextStyle(fontSize = 16.sp),
            placeholder = {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 4.dp),
                    textAlign = TextAlign.Start,
                    text = getLocalizedString(R.string.search_hint),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Black.copy(alpha = 0.66f),
                    maxLines = 1
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Black
                )
            },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    onImeSearch()
                }
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            trailingIcon = {
                AnimatedVisibility(
                    visible = searchQuery.isNotBlank()
                ) {
                    IconButton(
                        onClick = {
                            onSearchQueryChange("")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            },
        )
    }
}
