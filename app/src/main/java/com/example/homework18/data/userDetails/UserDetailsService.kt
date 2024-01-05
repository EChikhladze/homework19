package com.example.homework18.data.userDetails

import com.example.homework18.data.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserDetailsService {

    @GET("users/{id}")
    suspend fun getUserDetails(@Path("id") id: String): Response<User>
}