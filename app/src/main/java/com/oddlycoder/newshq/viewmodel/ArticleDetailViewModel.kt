package com.oddlycoder.newshq.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oddlycoder.newshq.model.Article
import com.oddlycoder.newshq.model.repository.Repository

class ArticleDetailViewModel : ViewModel() {

    private lateinit var articleDetail: Article

    fun setArticle(article: Article) {
         articleDetail = article
    }

    fun getArticle(): Article {
        return articleDetail
    }

}