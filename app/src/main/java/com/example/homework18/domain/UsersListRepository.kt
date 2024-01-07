package com.example.homework18.domain

import com.example.homework18.data.common.Resource
import kotlinx.coroutines.flow.Flow

interface UsersListRepository {
    suspend fun usersList(): Flow<Resource<List<UserDetailsResponse>>>
}