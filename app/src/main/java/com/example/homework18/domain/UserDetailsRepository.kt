package com.example.homework18.domain

import com.example.homework18.data.common.Resource
import kotlinx.coroutines.flow.Flow

interface UserDetailsRepository {
    suspend fun userDetails(id: Int): Flow<Resource<UserDetailsResponse>>
}