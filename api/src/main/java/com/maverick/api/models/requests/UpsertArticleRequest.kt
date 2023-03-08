package com.maverick.api.models.requests

import com.maverick.api.models.entities.ArticleData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpsertArticleRequest(
    @Json(name = "article")
    val article: ArticleData
)