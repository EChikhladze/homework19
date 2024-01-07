package com.example.homework18.data.userDetails

import com.example.homework18.data.common.Resource
import com.example.homework18.domain.UserDetailsRepository
import com.example.homework18.domain.UserDetailsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserDetailsRepositoryImpl @Inject constructor(private val userDetailsService: UserDetailsService) :
    UserDetailsRepository {
    override suspend fun userDetails(id: Int): Flow<Resource<UserDetailsResponse>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val response = userDetailsService.getUserDetails(id.toString())
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {
                        emit(Resource.Success(user.toDomain()))
                    } else {
                        emit(Resource.Error("User details not available"))
                    }
                } else {
                    emit(Resource.Error(response.message()))
                }
            } catch (e: IOException) {
                emit(Resource.Error("Network error: ${e.localizedMessage}"))
            } catch (e: HttpException) {
                emit(Resource.Error("HTTP error: ${e.localizedMessage}"))
            } catch (e: Throwable) {
                emit(Resource.Error("Unexpected error: ${e.localizedMessage}"))
            }
            emit(Resource.Loading(false))
        }
    }
}