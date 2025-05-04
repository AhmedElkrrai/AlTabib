package com.example.altabib.design_system.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.altabib.core.LocalImageStorage
import java.io.File

class LocalImageStorageImpl(
    private val context: Context,
    private val prefs: SharedPreferences
) : LocalImageStorage {

    companion object {
        private const val KEY_AVATAR_PATH = "avatar_path"
    }

    override fun saveAvatar(userId: String, bytes: ByteArray): String {
        deleteAvatar(userId)

        val file = File(context.filesDir, "$userId.jpg")
        file.writeBytes(bytes)
        val uri = file.toURI().toString()

        cacheAvatarPath(uri)
        return uri
    }

    override fun deleteAvatar(userId: String) {
        val file = File(context.filesDir, "$userId.jpg")
        if (file.exists()) {
            file.delete()
        }
        prefs.edit().remove(KEY_AVATAR_PATH).apply()
    }

    override fun isLocalAvatar(path: String): Boolean {
        return path.startsWith("file:")
    }

    override fun cacheAvatarPath(path: String) {
        prefs.edit().putString(KEY_AVATAR_PATH, path).apply()
    }

    override fun getCachedAvatarPath(): String {
        return prefs.getString(KEY_AVATAR_PATH, "") ?: ""
    }
}
