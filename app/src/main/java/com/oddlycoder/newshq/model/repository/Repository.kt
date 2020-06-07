package com.oddlycoder.newshq.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.oddlycoder.newshq.model.Article
import com.oddlycoder.newshq.model.News
import com.oddlycoder.newshq.model.data.remote.NewsBuilder

object Repository {

    /**
     * required results from data.remote: NewsBuilder.kt
     * viewModels should use these
     */

    private var newsBuilder = NewsBuilder

    fun getArticles(): LiveData<List<Article>> {
        return newsBuilder.getArticles()
    }

    fun getArticleCallFailed(): LiveData<Boolean> {
        return newsBuilder.getArticleFailureResult()
    }

}