package com.example.homework18.data.usersList

import com.example.homework18.data.common.Resource
import com.example.homework18.data.common.toDomain
import com.example.homework18.domain.UserDetailsResponse
import com.example.homework18.domain.UsersListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UsersListRepositoryImpl @Inject constructor(private val usersListService: UsersListService) :
    UsersListRepository {
    override suspend fun usersList(): Flow<Resource<List<UserDetailsResponse>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val response = usersListService.getUsersList()
                if (response.isSuccessful) {
                    val users = response.body()
                    if (users != null) {
                        emit(Resource.Success(users.map { it.toDomain() }))
                    } else {
                        emit(Resource.Error("Users list not available"))
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