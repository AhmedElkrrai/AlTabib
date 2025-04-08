package com.example.altabib.featuers.user.data.source.remote

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.user.domain.entities.User
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

private const val USERS_PATH = "users"

class AuthenticationService(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val googleSignInClient: GoogleSignInClient
) {

    suspend fun signInWithGoogle(idToken: String): Result<FirebaseUser, DataError> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                val authResult = firebaseAuth.signInWithCredential(credential).await()
                val user = authResult.user
                if (user != null) Result.Success(user)
                else Result.Error(DataError.AuthError.RetrievalError("User is null"))
            } catch (e: Exception) {
                Result.Error(
                    DataError.AuthError.RetrievalError(
                        e.message ?: "Could not sign in with Google"
                    )
                )
            }
        }

    suspend fun registerUser(user: User): Result<User, DataError> = withContext(Dispatchers.IO) {
        try {
            val currentUser = firebaseAuth.currentUser
                ?: return@withContext Result.Error(DataError.AuthError.RetrievalError("Not signed in"))

            val userDoc = firestore.collection(USERS_PATH).document(currentUser.uid)
            val snapshot = userDoc.get().await()

            return@withContext if (snapshot.exists()) {
                val existingUser = snapshot.toObject(User::class.java)!!
                Result.Success(existingUser)
            } else {
                val newUser = user.copy(uid = currentUser.uid)
                userDoc.set(newUser).await()
                Result.Success(newUser)
            }

        } catch (e: Exception) {
            Result.Error(
                DataError.AuthError.RetrievalError(
                    e.message ?: "Could not sign in with Google"
                )
            )
        }
    }

    fun logout() {
        firebaseAuth.signOut()
        googleSignInClient.signOut()
        googleSignInClient.revokeAccess()
    }
}
