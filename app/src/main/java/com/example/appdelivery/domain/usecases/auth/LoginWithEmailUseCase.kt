package com.example.appdelivery.domain.usecases.auth

import com.example.appdelivery.domain.repository.AuthRepository
import javax.inject.Inject

class LoginWithEmailUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke (email: String, password: String) = repository.loginWithEmail(email, password)
}

