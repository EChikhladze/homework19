package com.example.homework18.domain

import com.example.homework18.data.Resource
import com.example.homework18.data.User
import kotlinx.coroutines.flow.Flow

interface UserDetailsRepository {
    suspend fun userDetails(id: Int): Flow<Resource<User>>
}