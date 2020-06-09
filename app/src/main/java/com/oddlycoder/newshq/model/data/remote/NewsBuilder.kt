package com.oddlycoder.newshq.model.data.remote


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oddlycoder.newshq.model.Article
import com.oddlycoder.newshq.model.News
import retrofit2.*
import retrofit2.converter.jackson.JacksonConverterFactory

object NewsBuilder {

    private var articlesData = MutableLiveData<List<Article>>()
    private var articleCallFailed = MutableLiveData<Boolean>()

    init {
        val baseUrl = "https://learnappmaking.com/ex/"
        val key = "CHWGk3OTwgObtQxGqdLvVhwji6FsYm95oe87o3ju"

        /**
         * url request is built from here.
         * retrofit does that from this interface, check {@see NewsService.kt}
         * */
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

        val service = retrofit.create(NewsService::class.java)

        /**
         * make a get request after everything is setup
         * enqueue should do it async
         */
        val call = service.getNews(key)
        call.enqueue(object: Callback<News> {

            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (!response.isSuccessful) return

                val news = response.body()

                // set articles if safe
                articlesData.value = news?.articles as MutableList<Article>
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("NewsBuilder", "onFailure: something went wrong ", t)
                articleCallFailed.value = true
            }
        })
    }

    fun getArticleFailureResult(): LiveData<Boolean> {
        return articleCallFailed
    }

    fun getArticles(): LiveData<List<Article>> {
        return articlesData
    }

}