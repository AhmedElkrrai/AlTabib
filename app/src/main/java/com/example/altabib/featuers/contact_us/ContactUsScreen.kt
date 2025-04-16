package com.example.altabib.featuers.contact_us

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.altabib.R

@Composable
fun ContactUsDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var message by remember { mutableStateOf("") }
    val devEmail = stringResource(R.string.dev_email)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Contact Us") },
        text = {
            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                placeholder = { Text("Write your message here...") },
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (message.isNotBlank()) {
                        val emailIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "message/rfc822"
                            putExtra(Intent.EXTRA_EMAIL, arrayOf(devEmail))
                            putExtra(Intent.EXTRA_SUBJECT, "App Feedback")
                            putExtra(Intent.EXTRA_TEXT, message)
                        }

                        try {
                            context.startActivity(
                                Intent.createChooser(emailIntent, "Send Email")
                            )
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(context, "No email app found", Toast.LENGTH_SHORT).show()
                        }

                        onDismiss()
                    } else {
                        Toast.makeText(context, "Message cannot be empty", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            ) {
                Text("Send")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
