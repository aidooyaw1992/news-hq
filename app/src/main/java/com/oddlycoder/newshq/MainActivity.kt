package com.oddlycoder.newshq


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.oddlycoder.newshq.databinding.ActivityMainBinding
import com.oddlycoder.newshq.model.Article
import com.oddlycoder.newshq.netutils.NetworkInterface
import com.oddlycoder.newshq.netutils.NetworkUtils
import com.oddlycoder.newshq.view.ArticleDetailFragment
import com.oddlycoder.newshq.view.ArticlesFragment
import com.oddlycoder.newshq.view.NetworkStateFragment

class MainActivity : AppCompatActivity(),
    ArticlesFragment.FragmentCallbacks, ArticleDetailFragment.FragmentCallbacks {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            if (NetworkUtils.isNetworkConnected(applicationContext)) {
                Log.d(TAG, "onCreate: Network has capable internet")
                fragmentToOpenOnNetworkState(ArticlesFragment.newInstance())
            } else {
                fragmentToOpenOnNetworkState(NetworkStateFragment.newInstance())
            }
        }
    }

    private fun fragmentToOpenOnNetworkState(fragment: Fragment) {
        val container = supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
        val frag = container ?: fragment
        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainer.id, frag)
            .commit()
    }

    override fun onArticleSelected(article: Article) {
        val fragment = ArticleDetailFragment.newInstance(article)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("detail")
            .setCustomAnimations(R.anim.enter_right, R.anim.exit_left)
            .commit()
    }

    override fun onFragmentBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            supportFragmentManager.popBackStack()
        }
    }

}