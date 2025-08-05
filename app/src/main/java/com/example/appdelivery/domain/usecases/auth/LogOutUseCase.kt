package com.example.appdelivery.domain.usecases.auth

import com.example.appdelivery.domain.repository.AuthRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.logOut()
}