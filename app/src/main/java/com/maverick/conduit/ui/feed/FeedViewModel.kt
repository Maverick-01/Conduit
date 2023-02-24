package com.maverick.conduit.ui.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maverick.api.models.entities.Article
import com.maverick.conduit.data.ArticlesRepo
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {

    private val _feed = MutableLiveData<List<Article>>()
    val feed: LiveData<List<Article>> = _feed

    fun fetchGlobalFeed() = viewModelScope.launch {
        ArticlesRepo.api.getArticles().body()?.let {
            _feed.postValue(it.articles)
            Log.d("feed","${it.articlesCount}")
        }
    }
}