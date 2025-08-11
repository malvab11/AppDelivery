package com.example.appdelivery.data.network.firebase.dataSources

import com.example.appdelivery.data.models.UserModel

interface UserDataSource {
    suspend fun createUser(user: UserModel): Result<Unit>
    suspend fun updateUser(user: UserModel): Result<Unit>
    suspend fun deleteUser(uid: String): Result<Unit>
    suspend fun getUser(uid: String): Result<UserModel?>
}