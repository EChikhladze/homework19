package com.example.homework18.data.userDetails

import com.example.homework18.domain.UserDetailsResponse

fun UserDetailsDTO.toDomain(): UserDetailsResponse {
    with(this.data) {
        return UserDetailsResponse(
            id = id,
            email = email,
            firstName = firstName,
            lastName = lastName,
            avatar = avatar
        )
    }
}