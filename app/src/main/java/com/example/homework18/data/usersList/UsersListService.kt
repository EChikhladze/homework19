package com.example.homework18.data.usersList

import com.example.homework18.data.User
import retrofit2.Response
import retrofit2.http.GET

interface UsersListService {
    @GET("7ec14eae-06bf-4f6d-86d2-ac1b9df5fe3d")
    suspend fun getUsersList(): Response<List<User>>
}