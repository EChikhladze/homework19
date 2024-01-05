package com.example.homework18.data.usersList

import com.example.homework18.data.Resource
import com.example.homework18.data.User
import com.example.homework18.domain.UsersListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UsersListRepositoryImpl @Inject constructor(private val usersListService: UsersListService) :
    UsersListRepository {
    override suspend fun usersList(): Flow<Resource<List<User>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val response = usersListService.getUsersList()
                if (response.isSuccessful) {
                    emit(Resource.Success(response.body()!!))
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