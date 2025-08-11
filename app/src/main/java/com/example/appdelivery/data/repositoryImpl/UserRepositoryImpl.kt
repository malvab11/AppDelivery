package com.example.appdelivery.data.repositoryImpl

import com.example.appdelivery.data.mappers.UserMapper
import com.example.appdelivery.data.network.firebase.dataSources.UserDataSource
import com.example.appdelivery.domain.entities.user.UserEntity
import com.example.appdelivery.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun createUser(user: UserEntity): Result<Unit> {
        val userModel = UserMapper.toModel(user)
        return userDataSource.createUser(userModel)
    }

    override suspend fun updateUser(user: UserEntity): Result<Unit> {
        val userModel = UserMapper.toModel(user)
        return userDataSource.updateUser(userModel)
    }

    override suspend fun deleteUser(uid: String): Result<Unit> {
        return userDataSource.deleteUser(uid)
    }

    override suspend fun getUser(uid: String): Result<UserEntity?> {
        return userDataSource.getUser(uid).map { userModel ->
            userModel?.let { UserMapper.toEntity(it) }
        }
    }

}