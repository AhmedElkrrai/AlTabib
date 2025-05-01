package com.example.appointments.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.altabib.design_system.theme.Primary
import com.example.altabib.design_system.theme.Red
import com.example.altabib.design_system.utils.FormatCompose
import com.example.appointments.presentation.models.AppointmentUi

@Composable
fun AppointmentCard(
    modifier: Modifier = Modifier,
    appointment: AppointmentUi,
    onDismissClick: () -> Unit
) {
    FormatCompose {
        Card(
            modifier = modifier,
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Primary)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Dismiss icon
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Dismiss Appointment",
                    tint = Red,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp)
                        .align(Alignment.TopStart)
                        .clickable { onDismissClick() }
                )

                // Main card content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Avatar
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "avatar",
                        modifier = Modifier.size(100.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Name
                    Text(
                        text = appointment.patientName.value,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        }
    }
}
