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

    private val TAG = "ArticlesViewModel"
    private val repository: Repository = Repository

    private var articlesLiveData = MutableLiveData<List<Article>>()
    private var articlesList = mutableListOf<Article>()

    
    init { 
        start()
       // articlesLiveData.value = articlesList
    }

    // return articles from news results from repository
    fun start() {
        articlesList.let {
            repository.getArticles()
        }
        articlesLiveData.value = articlesList
    }

    fun allArticles(): MutableLiveData<List<Article>> {
        // return articlesLiveData
        return repository.getArticles() as MutableLiveData<List<Article>>
    }

}