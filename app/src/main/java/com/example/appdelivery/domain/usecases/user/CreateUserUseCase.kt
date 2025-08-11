package com.example.appdelivery.domain.usecases.user

import com.example.appdelivery.domain.entities.user.UserEntity
import com.example.appdelivery.domain.repository.UserRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: UserEntity) = userRepository.createUser(user = user)
}