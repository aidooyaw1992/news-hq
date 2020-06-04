package com.oddlycoder.newshq.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.oddlycoder.newshq.model.Article
import com.oddlycoder.newshq.model.News
import com.oddlycoder.newshq.model.data.remote.NewsBuilder

object Repository {

    private val TAG = "Repository"

    private var newsBuilder = NewsBuilder

    init {
        Log.d(TAG, "repository initialized")
       // newsBuilder.getArticles()
    }

    fun getArticles(): LiveData<List<Article>> {
        Log.d(TAG, "repository: -- live data.articles")
        return newsBuilder.getArticles()
    }

}