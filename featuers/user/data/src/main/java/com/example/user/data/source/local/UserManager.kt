package com.example.user.data.source.local

import android.content.SharedPreferences
import com.example.user.domain.entities.User
import com.google.gson.Gson

class UserManager(
    private val sharedPreferences: SharedPreferences
) {
    private val userKey = "user_key"

    fun cacheUser(user: User) {
        val json = Gson().toJson(user)
        sharedPreferences.edit().putString(userKey, json).apply()
    }

    fun getUser(): User? {
        val json = sharedPreferences.getString(userKey, null) ?: return null
        return Gson().fromJson(json, User::class.java)
    }

    fun clearUser() {
        sharedPreferences.edit().remove(userKey).apply()
    }
}
