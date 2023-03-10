package com.maverick.conduit.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverick.api.ConduitClient
import com.maverick.api.models.entities.Article
import com.maverick.conduit.data.ArticlesRepo
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {

    private val _article = MutableLiveData<Article>()
    val article: LiveData<Article> = _article
    val api = ConduitClient.publicApi

    fun fetchArticle(slug: String) = viewModelScope.launch {
        val response = api.getArticleBySlug(slug)
        response.body()?.article?.let {
            _article.postValue(it)
        }
    }

    fun createArticle(
        title: String?,
        body: String?,
        description: String?,
        tagList: List<String>? = null
    ) = viewModelScope.launch {
        val response = ArticlesRepo.createArticle(title, description, body, tagList)
    }
}