package com.example.appdelivery.domain.repository

import com.example.appdelivery.domain.entities.user.UserEntity

interface UserRepository {
    suspend fun createUser(user: UserEntity): Result<Unit>
    suspend fun updateUser(user: UserEntity): Result<Unit>
    suspend fun deleteUser(uid: String): Result<Unit>
    suspend fun getUser(uid: String): Result<UserEntity?>
}