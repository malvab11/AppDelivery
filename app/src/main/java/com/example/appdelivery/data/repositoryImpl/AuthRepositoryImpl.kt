package com.example.appdelivery.data.repositoryImpl

import com.example.appdelivery.data.network.firebase.dataSources.AuthDataSource
import com.example.appdelivery.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun loginWithEmail(email: String, password: String): Result<FirebaseUser> {
        return authDataSource.loginWithEmail(email, password)
    }

    override suspend fun registerWithEmail(email: String, password: String): Result<FirebaseUser> {
        return authDataSource.registerWithEmail(email, password)
    }

    override suspend fun loginWithGoogle(idToken: String): Result<FirebaseUser> {
        return authDataSource.loginWithGoogle(idToken)
    }

    override suspend fun loginWithFacebook(token: String): Result<FirebaseUser> {
        return authDataSource.loginWithFacebook(token)
    }

    override fun getCurrentUser(): FirebaseUser? {
        return authDataSource.getCurrentUser()
    }

    override fun logOut() {
        return authDataSource.logOut()
    }
}