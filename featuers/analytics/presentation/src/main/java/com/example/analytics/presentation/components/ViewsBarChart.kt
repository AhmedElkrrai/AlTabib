package com.example.analytics.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import com.example.analytics.presentation.util.ViewData
import kotlin.math.roundToInt

@Composable
fun ViewsBarChart(
    viewData: List<ViewData>,
    modifier: Modifier = Modifier
) {
    if (viewData.isEmpty()) return // Don't draw if no data

    val textMeasurer = rememberTextMeasurer()
    val textStyle = MaterialTheme.typography.bodySmall.copy(color = Color.Black)

    Canvas(modifier = modifier.padding(16.dp)) {
        val chartWidth = size.width - 60.dp.toPx() // Leave space for y-axis labels
        val chartHeight = size.height - 40.dp.toPx() // Leave space for x-axis labels
        val barSpacing = 4.dp.toPx() // Space between bars
        val maxViews = viewData.maxOfOrNull { it.viewCount }?.toFloat() ?: 1f
        val barCount = viewData.size
        val barWidth = (chartWidth - (barCount - 1) * barSpacing) / barCount

        // Calculate y-axis steps (e.g., 0, 5, 10, 15, ...)
        val yStepCount = 5
        val yStepValue = (maxViews / yStepCount).coerceAtLeast(1f).roundToInt().toFloat()
        val maxYValue = (yStepValue * yStepCount).coerceAtLeast(maxViews)

        // Draw y-axis labels and horizontal grid lines
        repeat(yStepCount + 1) { i ->
            val yValue = i * yStepValue
            val y = chartHeight - (yValue / maxYValue) * chartHeight
            val text = yValue.toInt().toString()
            val textLayoutResult = textMeasurer.measure(text, textStyle)
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(0f, y - textLayoutResult.size.height / 2f)
            )

            // Draw grid line
            drawLine(
                color = Color.Gray.copy(alpha = 0.2f),
                start = Offset(60.dp.toPx(), y),
                end = Offset(60.dp.toPx() + chartWidth, y),
                strokeWidth = 1.dp.toPx()
            )
        }

        // Draw bars and x-axis labels
        viewData.forEachIndexed { index, data ->
            val x = 60.dp.toPx() + index * (barWidth + barSpacing)
            val barHeight = (data.viewCount / maxYValue) * chartHeight
            val y = chartHeight - barHeight

            // Draw bar
            drawRect(
                color = Color.Blue,
                topLeft = Offset(x, y),
                size = Size(barWidth, barHeight)
            )

            // Draw x-axis label (day)
            val text = data.day.toString()
            val textLayoutResult = textMeasurer.measure(text, textStyle)
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    x + barWidth / 2 - textLayoutResult.size.width / 2,
                    chartHeight + 8.dp.toPx()
                )
            )
        }

        // Draw axes
        drawLine(
            color = Color.Black,
            start = Offset(60.dp.toPx(), 0f),
            end = Offset(60.dp.toPx(), chartHeight),
            strokeWidth = 2.dp.toPx()
        ) // Y-axis
        drawLine(
            color = Color.Black,
            start = Offset(60.dp.toPx(), chartHeight),
            end = Offset(60.dp.toPx() + chartWidth, chartHeight),
            strokeWidth = 2.dp.toPx()
        ) // X-axis
    }
}