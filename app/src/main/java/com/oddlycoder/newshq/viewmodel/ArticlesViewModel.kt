package com.oddlycoder.newshq.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.oddlycoder.newshq.model.Article
import com.oddlycoder.newshq.model.News
import com.oddlycoder.newshq.model.repository.Repository

class ArticlesViewModel : ViewModel() {

    private val repository: Repository = Repository.get()

    fun allArticles(): LiveData<List<Article>> {
        return repository.getArticles()
    }

    fun articleCallFailed(): LiveData<Boolean> {
        return repository.getArticleCallFailed()
    }

    fun cachedArticles(): LiveData<List<Article>> {
        return repository.getCachedArticles()
    }

    fun retryRequest() {
        return repository.retryRequest()
    }

}