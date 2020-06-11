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

    /** called when retrofit fails to retrieve articles */
    fun articleCall(): LiveData<Boolean> {
        return repository.getArticleCallFailed()
    }

    /**
     * called when articleCall is called
     * get user cache data[articles user bookmarked]
     * */
    fun cachedArticles(): LiveData<List<Article>> {
        return repository.getCachedArticles()
    }

}