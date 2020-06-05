package com.oddlycoder.newshq

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.oddlycoder.newshq.databinding.ActivityMainBinding
import com.oddlycoder.newshq.model.Article
import com.oddlycoder.newshq.view.ArticleDetailFragment
import com.oddlycoder.newshq.view.ArticlesFragment

class MainActivity : AppCompatActivity() {

    // setup activity view binding once
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    
    companion object {
        private const val TAG = "MainActivity"
        private lateinit var article: Article

        var networkConnected: Boolean = false

        fun setArticle(article: Article) {
            this.article = article
        }

        fun getArticle(): Article {
            return article
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (fragment == null) {
                // call factory instance of articles fragment
                val article = ArticlesFragment.newInstance()
                supportFragmentManager.beginTransaction().add(binding.fragmentContainer.id, article)
                    .commit()
            }

        }

        networkConnected = isNetworkConnected()
        Log.d(TAG, "has network: $networkConnected")
    }



    @RequiresApi(Build.VERSION_CODES.M)
    fun isNetworkConnected(): Boolean {
        val connectivity = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivity.activeNetwork
        val networkHasConnection = connectivity.getNetworkCapabilities(activeNetwork)
        return networkHasConnection != null &&
                networkHasConnection.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }


}