package com.maverick.api.services

import com.maverick.api.models.entities.UserUpdateData
import com.maverick.api.models.requests.UpsertArticleRequest
import com.maverick.api.models.requests.UserUpdateRequest
import com.maverick.api.models.responses.ArticleResponse
import com.maverick.api.models.responses.ArticlesResponse
import com.maverick.api.models.responses.ProfileResponse
import com.maverick.api.models.responses.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface ConduitAuthAPI {
    @GET("user")
    suspend fun getCurrentUser(): Response<UserResponse>

    @PUT("user")
    suspend fun updateCurrentUser(
        @Body userUpdateData: UserUpdateRequest
    ): Response<UserResponse>

    @GET("profile/{username}")
    suspend fun getProfile(
        @Path("username") username: String
    ): Response<ProfileResponse>

    @POST("profile/{username}/follow")
    suspend fun followProfile(
        @Path("username") username: String
    ): Response<ProfileResponse>

    @POST("profile/{username}/follow")
    suspend fun unfollowProfile(
        @Path("username") username: String
    ): Response<ProfileResponse>

    @GET("articles/feed")
    suspend fun getFeedArticles(): Response<ArticlesResponse>

    @POST("articles/{slug}/favorite")
    suspend fun favoriteArticle(
        @Path("slug") slug: String
    ): Response<ArticleResponse>

    @DELETE("articles/{slug}/favorite")
    suspend fun unFavoriteArticle(
        @Path("slug") slug: String
    ): Response<ArticleResponse>

    @POST("articles")
    suspend fun createArticle(
        @Body article: UpsertArticleRequest
    ) :Response<ArticleResponse>
}