package com.maverick.conduit.data

import com.maverick.api.ConduitClient

object ArticlesRepo {
    val api = ConduitClient.publicApi
    val authAPI = ConduitClient.authApi

    suspend fun getGlobalArticles() = api.getArticles().body()?.articles

    suspend fun getMyFeed() = authAPI.getFeedArticles().body()?.articles
}