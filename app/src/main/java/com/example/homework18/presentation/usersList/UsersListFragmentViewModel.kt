package com.example.homework18.presentation.usersList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework18.data.common.Resource
import com.example.homework18.domain.UserDetailsResponse
import com.example.homework18.domain.UsersListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListFragmentViewModel @Inject constructor(private val usersListRepository: UsersListRepository): ViewModel() {

    private val _responseFlow = MutableStateFlow<Resource<List<UserDetailsResponse>>>(Resource.Loading(false))
    val responseFlow: SharedFlow<Resource<List<UserDetailsResponse>>> = _responseFlow.asStateFlow()

    fun getUsersList() {
        viewModelScope.launch {
            usersListRepository.usersList().collect {
                _responseFlow.value = it
            }
        }
    }
}