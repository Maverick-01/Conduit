package com.maverick.api.models.requests


import com.maverick.api.models.entities.UserUpdateData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserUpdateRequest(
    @Json(name = "user")
    val user: UserUpdateData
)