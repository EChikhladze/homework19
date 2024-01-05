package com.example.homework18.di

import com.example.homework18.data.userDetails.UserDetailsService
import com.example.homework18.data.usersList.UsersListService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL_USERS_LIST = "https://run.mocky.io/v3/"
    private const val BASE_URL_USER_DETAILS = "https://reqres.in/api/"

    @Singleton
    @Provides
    @Named("UsersListRetrofit")
    fun provideUsersListRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_USERS_LIST)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
    }

    @Singleton
    @Provides
    @Named("UserDetailsRetrofit")
    fun provideUserDetailsRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_USER_DETAILS)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideUsersListService(@Named("UsersListRetrofit") retrofit: Retrofit): UsersListService {
        return retrofit.create(UsersListService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserDetailsService(@Named("UserDetailsRetrofit") retrofit: Retrofit): UserDetailsService {
        return retrofit.create(UserDetailsService::class.java)
    }
}