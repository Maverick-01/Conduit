package com.maverick.api.models.responses


import com.maverick.api.models.entities.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "user")
    val user: User
)