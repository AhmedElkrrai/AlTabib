package com.example.altabib.featuers.user.presentation.auth

import android.content.Context
import android.content.Intent
import com.example.altabib.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class GoogleSignInHelper(context: Context) {
    private val googleSignInClient: GoogleSignInClient

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    fun getGoogleSignInClient(): GoogleSignInClient {
        return googleSignInClient
    }

    fun getSignInAccountFromIntent(intent: Intent): GoogleSignInAccount? {
        return GoogleSignIn.getSignedInAccountFromIntent(intent).let {
            try {
                it.getResult(ApiException::class.java)
            } catch (e: ApiException) {
                null // Handle exceptions appropriately
            }
        }
    }
}
