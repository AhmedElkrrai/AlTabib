package com.example.altabib.core

interface LocalImageStorage {
    fun saveAvatar(userId: String, bytes: ByteArray): String
    fun deleteAvatar(userId: String)
    fun isLocalAvatar(path: String): Boolean
    fun cacheAvatarPath(path: String)
    fun getCachedAvatarPath(): String
}
