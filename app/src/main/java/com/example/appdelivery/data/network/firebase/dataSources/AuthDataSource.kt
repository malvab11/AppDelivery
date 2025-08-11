package com.example.appdelivery.data.network.firebase.dataSources

import com.google.firebase.auth.FirebaseUser

interface AuthDataSource {
    suspend fun loginWithEmail(email: String, password: String): Result<FirebaseUser>
    suspend fun registerWithEmail(email: String, password: String): Result<FirebaseUser>
    suspend fun loginWithGoogle(idToken: String): Result<FirebaseUser>
    suspend fun loginWithFacebook(token: String): Result<FirebaseUser>
    fun getCurrentUser(): FirebaseUser?
    fun logOut()
}