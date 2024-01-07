package com.example.homework18.domain

data class UserDetailsResponse(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
)
