package com.example.appdelivery.domain.usecases.auth

import com.example.appdelivery.domain.repository.AuthRepository
import javax.inject.Inject

class LoginWithFacebookUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(token: String) = repository.loginWithFacebook(token)
}