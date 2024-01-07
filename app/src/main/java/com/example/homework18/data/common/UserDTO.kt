package com.example.homework18.data.common

import com.squareup.moshi.Json

data class UserDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "email") val email: String,
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String,
    @Json(name = "avatar") val avatar: String
)
