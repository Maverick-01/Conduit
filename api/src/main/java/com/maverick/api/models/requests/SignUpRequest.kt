package com.maverick.api.models.requests


import com.maverick.api.models.entities.UserCredentials
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpRequest(
    @Json(name = "user")
    val user: UserCredentials
)