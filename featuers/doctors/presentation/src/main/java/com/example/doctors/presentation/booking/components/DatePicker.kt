package com.example.doctors.presentation.booking.components

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.altabib.design.R
import com.example.altabib.design_system.components.AppOutlinedButton
import com.example.altabib.design_system.localization.getLocalizedString
import com.example.doctors.presentation.booking.BookingAction
import com.example.doctors.presentation.booking.BookingState
import java.time.LocalDate

@Composable
fun DatePicker(
    modifier: Modifier = Modifier,
    state: BookingState,
    onAction: (BookingAction) -> Unit
) {
    val context = LocalContext.current
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val selected = LocalDate.of(year, month + 1, dayOfMonth)
                onAction(BookingAction.OnDateSelected(selected))
            },
            LocalDate.now().year,
            LocalDate.now().monthValue - 1,
            LocalDate.now().dayOfMonth
        ).apply {
            datePicker.minDate = System.currentTimeMillis()
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        AppOutlinedButton(
            text = state.selectedDate?.let {
                "${it.dayOfMonth}/${it.monthValue}/${it.year}"
            } ?: getLocalizedString(R.string.select_date),
            onClick = { datePickerDialog.show() },
            modifier = modifier
                .fillMaxWidth(0.6f)
                .height(48.dp)
        )
    }
}
