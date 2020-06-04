package com.oddlycoder.newshq.model.data.remote


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.oddlycoder.newshq.model.Article
import com.oddlycoder.newshq.model.News
import retrofit2.*
import retrofit2.converter.jackson.JacksonConverterFactory

object NewsBuilder {

    val TAG = "NewsBuilder"

    // articles from response
    private var articlesData = MutableLiveData<List<Article>>()
    private var articlesList = mutableListOf<Article>()

    init {
        val baseUrl = "https://learnappmaking.com/ex/"
        // todo: could do better: store some where later
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
                if (!response.isSuccessful){
                    Log.d(TAG, "onResponse: Something went wrong ${response.code()}")
                    return
                }
                Log.d(TAG, "onResponse: response success: ${response.code()}")
                val news = response.body()
                // set articles if non null
                articlesList = news?.articles as MutableList<Article>
                articlesData.value = articlesList
                // todo: for debugging.. remove later
                val rArticle = news.articles
                Log.d(TAG, "onResponse: Article: Articles Retrieve(${rArticle.size}) ${rArticle[1].title}")
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d(TAG, "onFailure: something went wrong", t)
            }
        })
    }


    fun getArticles(): LiveData<List<Article>> {
        return articlesData
    }

}