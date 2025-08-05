package com.example.appdelivery.di.modules

import com.example.appdelivery.data.repositoryImpl.AuthRepositoryImpl
import com.example.appdelivery.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository{
        return AuthRepositoryImpl(FirebaseAuth.getInstance())
    }
}