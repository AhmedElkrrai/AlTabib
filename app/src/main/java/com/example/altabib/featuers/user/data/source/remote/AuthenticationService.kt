package com.example.altabib.featuers.user.data.source.remote

import android.util.Log
import com.example.altabib.core.DataError
import com.example.altabib.core.Result
import com.example.altabib.featuers.user.domain.entities.Doctor
import com.example.altabib.featuers.user.domain.entities.Patient
import com.example.altabib.featuers.user.data.source.remote.mappers.toDomain
import com.example.altabib.featuers.user.domain.entities.User
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

const val PATIENTS_PATH = "patients"
const val DOCTORS_PATH = "doctors"

class AuthenticationService(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val googleSignInClient: GoogleSignInClient
) {

    suspend fun signInWithGoogle(idToken: String): Result<User, DataError> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                val authResult = firebaseAuth.signInWithCredential(credential).await()
                val user = authResult.user
                if (user != null) Result.Success(user.toDomain())
                else Result.Error(DataError.FailedToRetrieveData)
            } catch (e: Exception) {
                Log.e("AuthenticationService", "Error signing in with Google", e)
                Result.Error(
                    DataError.GeneralError
                )
            }
        }

    suspend fun registerPatient(patient: Patient) = registerUser(PATIENTS_PATH, patient)

    suspend fun registerDoctor(doctor: Doctor) = registerUser(DOCTORS_PATH, doctor)

    private suspend inline fun <reified T : Any> registerUser(
        path: String,
        user: T
    ): Result<T, DataError> = withContext(Dispatchers.IO) {
        try {
            val currentUser = firebaseAuth.currentUser
                ?: return@withContext Result.Error(DataError.GeneralError)
            val userDoc = firestore.collection(path).document(currentUser.uid)
            val snapshot = userDoc.get().await()

            if (snapshot.exists()) {
                val existingUser = snapshot.toObject(T::class.java)!!
                Result.Success(existingUser)
            } else {
                userDoc.set(user).await()
                Result.Success(user)
            }
        } catch (e: Exception) {
            Log.e("AuthenticationService", "Error registering user", e)
            Result.Error(DataError.GeneralError)
        }
    }

    fun logout() {
        firebaseAuth.signOut()
        googleSignInClient.signOut()
        googleSignInClient.revokeAccess()
    }
}
