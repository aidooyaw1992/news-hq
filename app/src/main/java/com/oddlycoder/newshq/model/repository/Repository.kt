package com.oddlycoder.newshq.model.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import com.oddlycoder.newshq.model.Article
import com.oddlycoder.newshq.model.ArticleTbl
import com.oddlycoder.newshq.model.News
import com.oddlycoder.newshq.model.data.local.ArticleDatabase
import com.oddlycoder.newshq.model.data.remote.NewsBuilder
import java.lang.IllegalStateException

class Repository private constructor(val context: Context) {

    private var newsBuilder = NewsBuilder

    companion object {
        private var INSTANCE: Repository? = null
        private const val DATABASE = "articles-database"

        /** context is required to setup room. which is obtained from application context */
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = Repository(context)
            }
        }

        fun get(): Repository {
            return INSTANCE ?: throw IllegalStateException("Repository isn't initialize")
        }

    }
    /**
     * room builder: sets up database
     * */
    private var database: ArticleDatabase = Room.databaseBuilder(
        context.applicationContext,
        ArticleDatabase::class.java,
        DATABASE
    ).build()

    /** access dao */
    private val articlesDao = database.articlesDao()

    /** get cached.. */
    fun getCachedArticles(): LiveData<List<Article>> = articlesDao.queryAllArticles()

    suspend fun cacheArticles(article: Article) {
        /** set article tbl with article */
        val articleTbl = convertArticles(article)
        /** call dao insert */
        articlesDao.insertIntoArticles(articleTbl)
        showCacheToast("Article was bookmarked")

        /*if (queryRes != null) {
            showCacheToast("Article was bookmarked")
        } else {
            showCacheToast("Article failed to be bookmarked")
        }*/

    }

    private fun showCacheToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    private fun convertArticles(article: Article): ArticleTbl {
        return ArticleTbl(
            article.id!!,
            article.url!!,
            article.title!!,
            article.text!!,
            article.publisher!!,
            article.author!!,
            article.image!!,
            article.date!!
        )
    }

    /**
     * required results from data.remote: NewsBuilder.kt
     * viewModels should use these
     */

    fun getArticles(): LiveData<List<Article>> {
        return newsBuilder.getArticles()
    }

    fun getArticleCallFailed(): LiveData<Boolean> {
        return newsBuilder.getArticleFailureResult()
    }

}