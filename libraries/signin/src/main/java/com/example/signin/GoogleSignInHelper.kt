package com.example.signin

import android.content.Context
import android.content.Intent
import com.example.altabib.signin.BuildConfig
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class GoogleSignInHelper(context: Context) {
    private val googleSignInClient: GoogleSignInClient

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.DEFAULT_WEB_CLIENT_ID)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    fun getClient(): GoogleSignInClient {
        return googleSignInClient
    }

    fun getSignInIntent(): Intent {
        return getClient().signInIntent
    }

    fun getToken(intent: Intent?): String? {
        return GoogleSignIn.getSignedInAccountFromIntent(intent)
            .getResult(ApiException::class.java)
            .idToken
    }
}
