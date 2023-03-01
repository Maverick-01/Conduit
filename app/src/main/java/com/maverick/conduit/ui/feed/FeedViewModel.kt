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
        ArticlesRepo.getGlobalArticles()?.let {
            _feed.postValue(it)
            Log.d("global feed", "${it.size}")
        }
    }

    fun fetchMyFeed() = viewModelScope.launch {
        ArticlesRepo.getMyFeed()?.let {
            _feed.postValue(it)
            Log.d("my feed", "${it.size}")
        }
    }
}