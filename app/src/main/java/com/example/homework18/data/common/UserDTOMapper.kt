package com.example.homework18.data.common

import com.example.homework18.data.common.UserDTO
import com.example.homework18.domain.UserDetailsResponse

fun UserDTO.toDomain(): UserDetailsResponse {
    return with(this) {
        UserDetailsResponse(
            id = id,
            email = email,
            firstName = firstName,
            lastName = lastName,
            avatar = avatar
        )
    }
}