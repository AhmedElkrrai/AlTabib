package com.example.altabib.design_system.utils

import android.content.Context
import android.net.Uri

interface ByteArrayConverter {
    fun uriToBytes(uri: Any): ByteArray?
}

class ByteArrayConverterImpl(
    private val context: Context
) : ByteArrayConverter {

    override fun uriToBytes(uri: Any): ByteArray? {
        return try {
            if (uri !is Uri) return null
            val inputStream = context.contentResolver.openInputStream(uri)
            inputStream?.use { it.readBytes() }
        } catch (e: Exception) {
            null
        }
    }
}
