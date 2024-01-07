package com.example.homework18.data.common

sealed class Resource<D> {
    data class Success<D>(val data: D) : Resource<D>()
    data class Error<D>(val errorMessage: String) : Resource<D>()
    data class Loading<D>(val loading: Boolean) : Resource<D>()
}