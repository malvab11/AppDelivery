package com.example.appdelivery.domain.usecases.auth

import com.example.appdelivery.domain.repository.AuthRepository
import javax.inject.Inject

class LoginWithGoogleUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(idToken: String) = repository.loginWithGoogle(idToken)
}