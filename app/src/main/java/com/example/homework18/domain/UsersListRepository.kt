package com.example.homework18.domain

import com.example.homework18.data.Resource
import com.example.homework18.data.User
import kotlinx.coroutines.flow.Flow

interface UsersListRepository {
    suspend fun usersList(): Flow<Resource<List<User>>>
}