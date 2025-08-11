package com.example.appdelivery.data.network.firebase.dataSourcesImpl

import com.example.appdelivery.data.network.firebase.dataSources.AuthDataSource
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthDataSource {
    override suspend fun loginWithEmail(email: String, password: String): Result<FirebaseUser> {
        return try {
            val response = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Result.success(response.user!!)
        } catch (e: Exception) {
            Result.failure<FirebaseUser>(e)
        }
    }

    override suspend fun registerWithEmail(email: String, password: String): Result<FirebaseUser> {
        return try {
            val response = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Result.success(response.user!!)
        } catch (e: Exception) {
            Result.failure<FirebaseUser>(e)
        }
    }

    override suspend fun loginWithGoogle(idToken: String): Result<FirebaseUser> {
        return try {
            val credentials = GoogleAuthProvider.getCredential(idToken, null)
            val response = firebaseAuth.signInWithCredential(credentials).await()
            Result.success(response.user!!)
        } catch (e: Exception) {
            Result.failure<FirebaseUser>(e)
        }
    }

    override suspend fun loginWithFacebook(token: String): Result<FirebaseUser> {
        return try {
            val credentials = FacebookAuthProvider.getCredential(token)
            val response = firebaseAuth.signInWithCredential(credentials).await()
            Result.success(response.user!!)
        } catch (e: Exception) {
            Result.failure<FirebaseUser>(e)
        }
    }

    override fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser

    override fun logOut() = firebaseAuth.signOut()
}