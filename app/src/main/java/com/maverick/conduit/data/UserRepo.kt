package com.maverick.conduit.data

import com.maverick.api.ConduitClient
import com.maverick.api.models.entities.LoginData
import com.maverick.api.models.entities.User
import com.maverick.api.models.entities.UserCredentials
import com.maverick.api.models.requests.LoginRequest
import com.maverick.api.models.requests.SignUpRequest

object UserRepo {
    val api = ConduitClient.publicApi
    val authApi = ConduitClient.authApi

    suspend fun login(email:String,password:String): User? {
        val response = api.loginUser(LoginRequest(LoginData(email,password)))

        //TODO: save it in sharedpreference
        ConduitClient.authToken = response.body()?.user?.token
        return response.body()?.user
    }

    suspend fun signup(username:String,email: String,password: String): User? {
        val response = api.signUpUser(SignUpRequest(UserCredentials(email, password, username)))
        ConduitClient.authToken = response.body()?.user?.token
        return response.body()?.user
    }
    suspend fun getUserProfile() = authApi.getCurrentUser().body()?.user
}