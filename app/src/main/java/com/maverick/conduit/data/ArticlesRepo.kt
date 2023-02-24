package com.maverick.conduit.data

import com.maverick.api.ConduitClient
import com.maverick.api.services.ConduitAPI

object ArticlesRepo {
    val api = ConduitClient().api

    suspend fun getGlobalArticles() = api.getArticles()
}