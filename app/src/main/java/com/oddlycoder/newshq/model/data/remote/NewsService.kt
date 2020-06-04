package com.oddlycoder.newshq.model.data.remote

import com.oddlycoder.newshq.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

      // uri get request -- url should be constructed in builder by retrofit
      @GET("news/articles/Apple")
      fun getNews(@Query("secret") secret: String): Call<News>

}