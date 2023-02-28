package com.maverick.api.services

import com.maverick.api.models.requests.LoginRequest
import com.maverick.api.models.requests.SignUpRequest
import com.maverick.api.models.responses.ArticleResponse
import com.maverick.api.models.responses.ArticlesResponse
import com.maverick.api.models.responses.TagsResponse
import com.maverick.api.models.responses.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ConduitAPI {

    @POST("users")
    suspend fun signUpUser(
        @Body userCredentials: SignUpRequest
    ): Response<UserResponse>

    @POST("users/login")
    suspend fun loginUser(
        @Body userCredentials: LoginRequest
    ): Response<UserResponse>

    @GET("articles")
    suspend fun getArticles(
        @Query("author") author: String? = null,
        @Query("favorited") favorited: String? = null,
        @Query("tag") tag: String? = null
    ): Response<ArticlesResponse>

    @GET("articles/{slug}")
    suspend fun getArticleBySlug(
        @Path("slug") slug: String
    ): Response<ArticleResponse>

    @GET("tags")
    suspend fun getTags(): Response<TagsResponse>
}