package com.example.homework18.presentation.userDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework18.data.common.Resource
import com.example.homework18.domain.UserDetailsRepository
import com.example.homework18.domain.UserDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsFragmentViewModel @Inject constructor(private val userDetailsRepository: UserDetailsRepository) :
    ViewModel() {

    private val _responseFlow = MutableStateFlow<Resource<UserDetailsResponse>>(Resource.Loading(false))
    val responseFlow: SharedFlow<Resource<UserDetailsResponse>> = _responseFlow.asStateFlow()

    fun getUserDetails(id: Int) {
        viewModelScope.launch {
            userDetailsRepository.userDetails(id).collect {
                _responseFlow.value = it
            }
        }
    }
}