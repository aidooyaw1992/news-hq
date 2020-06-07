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
import com.oddlycoder.newshq.view.NetworkStateFragment

class MainActivity : AppCompatActivity() {

    private lateinit var networkConnectivity: NetworkUtils

    // setup activity view binding once
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        networkConnectivity = NetworkUtils()
        networkConnectivity.isNetworkConnected(applicationContext)

        val networkState = networkConnectivity.hasNetwork()

        if (networkState) {
            Log.d(TAG, "onCreate: Network has capable internet")
            if (savedInstanceState == null) {
                val article = ArticlesFragment.newInstance()
                initFragment(article)
            }
        } else {
            val networkStateFrag = NetworkStateFragment.newInstance()
            initFragment(networkStateFrag)
        }
    }

    private fun initFragment(fragment: Fragment) {
        val container = supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
        val frag = container ?: fragment
        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainer.id, frag)
            .commit()
    }



}