package com.maverick.conduit.data

import com.maverick.api.ConduitClient
import com.maverick.api.models.entities.LoginData
import com.maverick.api.models.requests.LoginRequest
import com.maverick.api.models.responses.UserResponse

object UserRepo {
    val api = ConduitClient().api

    suspend fun login(email:String,password:String): UserResponse? {
        val response = api.loginUser(LoginRequest(LoginData(email,password)))
        return response.body()
    }
}