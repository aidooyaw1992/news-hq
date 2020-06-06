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

    // setup activity view binding
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (fragment == null) {
                // setup article fragment from factory
                val article = ArticlesFragment.newInstance()
                supportFragmentManager.beginTransaction().add(binding.fragmentContainer.id, article)
                    .commit()
            }
        }

    }




}