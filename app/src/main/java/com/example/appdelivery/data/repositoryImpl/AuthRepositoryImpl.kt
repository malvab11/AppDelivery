package com.example.appdelivery.data.repositoryImpl

import com.example.appdelivery.domain.repository.AuthRepository
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {

    override suspend fun loginWithEmail(email: String, password: String): Result<FirebaseUser> {
        return try {
            val response = auth.signInWithEmailAndPassword(email, password).await()
            Result.success(response.user!!)
        } catch (e: Exception) {
            Result.failure<FirebaseUser>(e)
        }
    }

    override suspend fun registerWithEmail(email: String, password: String): Result<FirebaseUser> {
        return try {
            val response = auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(response.user!!)
        } catch (e: Exception) {
            Result.failure<FirebaseUser>(e)
        }
    }

    override suspend fun loginWithGoogle(idToken: String): Result<FirebaseUser> {
        return try {
            val credentials = GoogleAuthProvider.getCredential(idToken, null)
            val response = auth.signInWithCredential(credentials).await()
            Result.success(response.user!!)
        } catch (e: Exception) {
            Result.failure<FirebaseUser>(e)
        }
    }

    override suspend fun loginWithFacebook(token: String): Result<FirebaseUser> {
        return try {
            val credentials = FacebookAuthProvider.getCredential(token)
            val response = auth.signInWithCredential(credentials).await()
            Result.success(response.user!!)
        } catch (e: Exception) {
            Result.failure<FirebaseUser>(e)
        }
    }

    override fun getCurrentUser(): FirebaseUser? = auth.currentUser

    override fun logOut() = auth.signOut()
}