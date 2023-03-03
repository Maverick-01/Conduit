package com.maverick.conduit.data

import com.maverick.api.ConduitClient
import com.maverick.api.models.entities.LoginData
import com.maverick.api.models.entities.User
import com.maverick.api.models.entities.UserCredentials
import com.maverick.api.models.entities.UserUpdateData
import com.maverick.api.models.requests.LoginRequest
import com.maverick.api.models.requests.SignUpRequest
import com.maverick.api.models.requests.UserUpdateRequest

object UserRepo {
    val api = ConduitClient.publicApi
    val authApi = ConduitClient.authApi

    suspend fun login(email: String, password: String): User? {
        val response = api.loginUser(LoginRequest(LoginData(email, password)))

        ConduitClient.authToken = response.body()?.user?.token
        return response.body()?.user
    }

    suspend fun signup(username: String, email: String, password: String): User? {
        val response = api.signUpUser(SignUpRequest(UserCredentials(email, password, username)))
        ConduitClient.authToken = response.body()?.user?.token
        return response.body()?.user
    }

    suspend fun getUserProfile() = authApi.getCurrentUser().body()?.user

    suspend fun getCurrentUser(token: String): User? {
        ConduitClient.authToken = token
        return authApi.getCurrentUser().body()?.user
    }

    suspend fun updateUser(
        bio: String?,
        username: String?,
        email: String?,
        password: String?,
        image: String?
    ): User? {
        val response =
            authApi.updateCurrentUser(
                UserUpdateRequest(
                    UserUpdateData(
                        bio,
                        email,
                        image,
                        username,
                        password
                    )
                )
            )
        return response.body()?.user
    }
}