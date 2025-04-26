package com.example.user.data.source.remote

import android.content.Context
import com.example.altabib.user.data.BuildConfig
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

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
}
