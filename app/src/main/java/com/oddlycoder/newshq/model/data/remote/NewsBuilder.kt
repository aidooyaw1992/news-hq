package com.oddlycoder.newshq.model.data.remote


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oddlycoder.newshq.model.Article
import com.oddlycoder.newshq.model.News
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.jackson.JacksonConverterFactory

object NewsBuilder {

    // articles from response
    private var articlesData = MutableLiveData<List<Article>>()

    init {
        val baseUrl = "https://learnappmaking.com/ex/"
        val key = "CHWGk3OTwgObtQxGqdLvVhwji6FsYm95oe87o3ju"


        // retrofit implements service interface, builds url
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

        // client setup
        val service = retrofit.create(NewsService::class.java)

        // make request
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
            }
        })
    }

    fun getArticles(): LiveData<List<Article>> {
        return articlesData
    }

}