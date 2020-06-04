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
import com.oddlycoder.newshq.databinding.ActivityMainBinding
import com.oddlycoder.newshq.view.ArticleDetailFragment
import com.oddlycoder.newshq.view.ArticlesFragment

class MainActivity : AppCompatActivity() {

    // setup activity view binding once
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    
    companion object {
        private const val TAG = "MainActivity"
        var networkConnected: Boolean = false
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            // always return single of articles fragment
            val article = ArticlesFragment.newInstance()
            supportFragmentManager.beginTransaction().add(binding.fragmentContainer.id, article)
                .commit()
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