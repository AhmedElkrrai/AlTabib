package com.example.altabib.design_system.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
    color: Color = Primary
) {
    if (viewData.isEmpty()) return // Don't draw if no data

    val textMeasurer = rememberTextMeasurer()
    val textStyle = MaterialTheme.typography.bodySmall.copy(
        color = Color.White,
        fontSize = 10.sp
    )

    // Calculate dynamic canvas width based on number of bars
    val barWidth = 24.dp // Fixed bar width for readability
    val barSpacing = 4.dp // Space between bars
    val yAxisMargin = 40.dp // Space for y-axis labels
    val canvasWidth =
        yAxisMargin + (barWidth + barSpacing) * viewData.size // Total width for all bars

    Box(
        modifier = modifier
            .horizontalScroll(rememberScrollState()) // Enable horizontal scrolling
            .width(canvasWidth) // Set canvas width dynamically
    ) {
        Canvas(
            modifier = modifier
        ) {
            // Chart width: canvas width minus padding and y-axis margin
            val chartWidth = canvasWidth.toPx() - 32.dp.toPx() - yAxisMargin.toPx()
            val chartHeight = size.height - 40.dp.toPx() // Space for x-axis labels
            val maxViews = viewData.maxOfOrNull { it.viewCount }?.toFloat() ?: 1f

            // Calculate y-axis steps (e.g., 0, 5, 10, 15, ...)
            val yStepCount = 5
            val yStepValue = (maxViews / yStepCount).coerceAtLeast(1f).roundToInt().toFloat()
            val maxYValue = (yStepValue * yStepCount).coerceAtLeast(maxViews)

            // Draw y-axis labels and horizontal grid lines
            repeat(yStepCount + 1) { i ->
                val yValue = i * yStepValue
                val y = chartHeight - (yValue / maxYValue) * chartHeight
                val text = yValue.toInt().toString() // Use actual view count value
                val textLayoutResult = textMeasurer.measure(text, textStyle)
                drawText(
                    textLayoutResult = textLayoutResult,
                    topLeft = Offset(
                        yAxisMargin.toPx() - textLayoutResult.size.width - 4.dp.toPx(),
                        y - textLayoutResult.size.height / 2f
                    )
                )

                // Draw grid line across chart width
                drawLine(
                    color = Color.Gray.copy(alpha = 0.2f),
                    start = Offset(yAxisMargin.toPx(), y),
                    end = Offset(yAxisMargin.toPx() + chartWidth, y),
                    strokeWidth = 1.dp.toPx()
                )
            }

            // Draw bars and x-axis labels
            viewData.forEachIndexed { index, data ->
                val x = yAxisMargin.toPx() + index * (barWidth + barSpacing).toPx()
                val barHeight = (data.viewCount / maxYValue) * chartHeight
                val y = chartHeight - barHeight

                // Draw bar
                drawRect(
                    color = color,
                    topLeft = Offset(x, y),
                    size = Size(barWidth.toPx(), barHeight)
                )

                // Draw x-axis label (day)
                val text = data.day.toString()
                val textLayoutResult = textMeasurer.measure(text, textStyle)
                drawText(
                    textLayoutResult = textLayoutResult,
                    topLeft = Offset(
                        x + barWidth.toPx() / 2 - textLayoutResult.size.width / 2,
                        chartHeight + 8.dp.toPx()
                    )
                )
            }

            // Draw axes
            drawLine(
                color = Color.DarkGray,
                start = Offset(yAxisMargin.toPx(), 0f),
                end = Offset(yAxisMargin.toPx(), chartHeight),
                strokeWidth = 2.dp.toPx()
            ) // Y-axis
            drawLine(
                color = Color.DarkGray,
                start = Offset(yAxisMargin.toPx(), chartHeight),
                end = Offset(yAxisMargin.toPx() + chartWidth, chartHeight),
                strokeWidth = 2.dp.toPx()
            ) // X-axis
        }
    }
}
