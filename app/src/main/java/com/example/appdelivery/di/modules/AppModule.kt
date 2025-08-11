package com.example.appdelivery.di.modules

import com.example.appdelivery.data.network.firebase.dataSources.AuthDataSource
import com.example.appdelivery.data.network.firebase.dataSources.UserDataSource
import com.example.appdelivery.data.network.firebase.dataSourcesImpl.AuthDataSourceImpl
import com.example.appdelivery.data.network.firebase.dataSourcesImpl.UserDataSourceImpl
import com.example.appdelivery.data.repositoryImpl.AuthRepositoryImpl
import com.example.appdelivery.data.repositoryImpl.UserRepositoryImpl
import com.example.appdelivery.domain.repository.AuthRepository
import com.example.appdelivery.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providesUserDataSource(firebaseStore: FirebaseFirestore): UserDataSource =
        UserDataSourceImpl(firebaseStore = firebaseStore)


    @Provides
    @Singleton
    fun providesAuthDataSource(firebaseAuth: FirebaseAuth): AuthDataSource =
        AuthDataSourceImpl(firebaseAuth = firebaseAuth)

    @Provides
    @Singleton
    fun providesUserRepository(userDataSource: UserDataSource): UserRepository =
        UserRepositoryImpl(userDataSource = userDataSource)

    @Provides
    @Singleton
    fun providesAuthRepository(authDataSource: AuthDataSource): AuthRepository =
        AuthRepositoryImpl(authDataSource = authDataSource)

}