package com.oddlycoder.newshq.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oddlycoder.newshq.model.Article
import com.oddlycoder.newshq.model.ArticleTbl
import com.oddlycoder.newshq.model.repository.Repository
import kotlinx.coroutines.launch

class ArticleDetailViewModel : ViewModel() {

    private lateinit var articleDetail: Article

    private val repository: Repository? = Repository.get()

    fun setArticle(article: Article) {
         articleDetail = article
    }

    fun getArticle(): Article {
        return articleDetail
    }

    fun cacheCurrentArticle(article: Article) {
        viewModelScope.launch {
            repository?.cacheArticles(article)
        }
    }

}