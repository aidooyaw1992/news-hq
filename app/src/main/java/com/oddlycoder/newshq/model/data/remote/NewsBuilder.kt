package com.oddlycoder.newshq.model.data.remote


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oddlycoder.newshq.R
import com.oddlycoder.newshq.model.Article
import com.oddlycoder.newshq.model.News
import retrofit2.*
import retrofit2.converter.jackson.JacksonConverterFactory
import java.lang.IllegalStateException

class NewsBuilder private constructor(val context: Context) {

    private var articlesData = MutableLiveData<List<Article>>()
    private var articleCallFailed = MutableLiveData<Boolean>()
    private lateinit var articleCall: Call<News>

    companion object {
        private var INSTANCE: NewsBuilder? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = NewsBuilder(context)
            }
        }

        fun get(): NewsBuilder {
            return INSTANCE ?: throw IllegalStateException("Failed to initialize NewsBuilder")
        }
    }

    init {
        val baseUrl = "https://learnappmaking.com/ex/"
        val key = context.getString(R.string.url_key)

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

        // create implementation of endpoint
        val service = retrofit.create(NewsService::class.java)
        val call = service.getNews(key)

        call.enqueue(RequestCallback())
    }

    // clone request for retries
    fun cloneRequest() {
        articleCall.clone().enqueue(RequestCallback())
        Toast.makeText(context, "Retrying call", Toast.LENGTH_SHORT).show()
    }

    fun getArticleFailureResult(): LiveData<Boolean> {
        return articleCallFailed
    }

    fun getArticles(): LiveData<List<Article>> {
        return articlesData
    }

    private inner class RequestCallback : Callback<News> {
        override fun onResponse(call: Call<News>, response: Response<News>) {
            if (!response.isSuccessful) return

            val news = response.body()
            articlesData.value = news?.articles as MutableList<Article>
        }

        override fun onFailure(call: Call<News>, t: Throwable) {
            Log.d("NewsBuilder", "onFailure: something went wrong ", t)
            articleCallFailed.value = true
            articleCall = call

        }
    }

}