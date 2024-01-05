package com.example.homework18.di

import com.example.homework18.data.userDetails.UserDetailsRepositoryImpl
import com.example.homework18.data.userDetails.UserDetailsService
import com.example.homework18.data.usersList.UsersListRepositoryImpl
import com.example.homework18.data.usersList.UsersListService
import com.example.homework18.domain.UserDetailsRepository
import com.example.homework18.domain.UsersListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUsersListRepository(usersListService: UsersListService): UsersListRepository {
        return UsersListRepositoryImpl(usersListService)
    }

    @Singleton
    @Provides
    fun provideUserDetailsRepository(userDetailsService: UserDetailsService): UserDetailsRepository {
        return UserDetailsRepositoryImpl(userDetailsService)
    }
}