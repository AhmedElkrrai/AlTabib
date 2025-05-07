package com.example.analytics.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.altabib.design.R
import com.example.altabib.design_system.localization.getLocalizedString

@Composable
fun Disclaimer(
    modifier: Modifier = Modifier,
    cap: Int
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buildAnnotatedString {
                val txt1 = getLocalizedString(R.string.disclaimer_1)
                val txt2 = getLocalizedString(R.string.disclaimer_2, cap)
                val txt3 = getLocalizedString(R.string.disclaimer_3)
                append("$txt1 ")
                append("\n")
                withStyle(SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold)) {
                    append(txt2)
                }
                append("\n")
                append(txt3)
            },
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color.Gray,
                textAlign = TextAlign.Start,
                fontSize = 12.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )
    }
}
