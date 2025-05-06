package com.example.altabib.design_system.components

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
import androidx.compose.ui.unit.sp
import com.example.altabib.core.models.ViewData
import com.example.altabib.design_system.theme.Primary
import kotlin.math.roundToInt

@Composable
fun ViewsBarChart(
    viewData: List<ViewData>,
    modifier: Modifier = Modifier,
    barColor: Color = Primary,
) {
    if (viewData.isEmpty()) return // Don't draw if no data

    val textMeasurer = rememberTextMeasurer()
    // Reduce text size to prevent crowding, keep color white
    val textStyle = MaterialTheme.typography.bodySmall.copy(
        color = Color.White,
        fontSize = 10.sp // Smaller font size for better fit
    )

    Canvas(modifier = modifier.padding(16.dp)) {
        val yAxisMargin = 10.dp.toPx()
        val chartWidth = size.width - 10.dp.toPx() - yAxisMargin
        val chartHeight = size.height - 40.dp.toPx() // Space for x-axis labels
        val barSpacing = 4.dp.toPx() // Space between bars
        val maxViews = viewData.maxOfOrNull { it.viewCount }?.toFloat() ?: 1f
        val barCount = viewData.size
        // Calculate bar width to span full chart width
        val barWidth = (chartWidth - (barCount - 1) * barSpacing) / barCount.coerceAtLeast(1)

        // Calculate y-axis steps
        val yStepCount = 5
        val yStepValue = (maxViews / yStepCount).coerceAtLeast(1f).roundToInt().toFloat()
        val maxYValue = (yStepValue * yStepCount).coerceAtLeast(maxViews)

        // Draw y-axis labels and horizontal grid lines
        repeat(yStepCount + 1) { i ->
            val yValue = i * yStepValue
            val y = chartHeight - (yValue / maxYValue) * chartHeight
            val text = yValue.toInt().toString()
            val textLayoutResult = textMeasurer.measure(text, textStyle)
            // Position labels with enough space from left edge
            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    yAxisMargin - textLayoutResult.size.width - 4.dp.toPx(),
                    y - textLayoutResult.size.height / 2f
                )
            )

            // Draw grid line across chart width
            drawLine(
                color = Color.Gray.copy(alpha = 0.2f),
                start = Offset(yAxisMargin, y),
                end = Offset(yAxisMargin + chartWidth, y),
                strokeWidth = 1.dp.toPx()
            )
        }

        // Draw bars and x-axis labels
        viewData.forEachIndexed { index, data ->
            val x = yAxisMargin + index * (barWidth + barSpacing) // Start at yAxisMargin
            val barHeight = (data.viewCount / maxYValue) * chartHeight
            val y = chartHeight - barHeight

            // Draw bar
            drawRect(
                color = barColor,
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
            color = Color.DarkGray,
            start = Offset(yAxisMargin, 0f),
            end = Offset(yAxisMargin, chartHeight),
            strokeWidth = 2.dp.toPx()
        ) // Y-axis
        drawLine(
            color = Color.DarkGray,
            start = Offset(yAxisMargin, chartHeight),
            end = Offset(yAxisMargin + chartWidth, chartHeight),
            strokeWidth = 2.dp.toPx()
        ) // X-axis
    }
}