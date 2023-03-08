package com.maverick.conduit.data

import com.maverick.api.ConduitClient
import com.maverick.api.models.entities.Article
import com.maverick.api.models.entities.ArticleData
import com.maverick.api.models.requests.UpsertArticleRequest

object ArticlesRepo {
    val api = ConduitClient.publicApi
    val authAPI = ConduitClient.authApi

    suspend fun getGlobalArticles() = api.getArticles().body()?.articles
    suspend fun getMyFeed() = authAPI.getFeedArticles().body()?.articles

    suspend fun createArticle(
        title: String?,
        description: String?,
        body: String?,
        tabList: List<String>? = null
    ): Article? {
        val response = authAPI.createArticle(
            UpsertArticleRequest(
                ArticleData(
                    body,
                    description,
                    tabList,
                    title
                )
            )
        )
        return response.body()?.article
    }
}