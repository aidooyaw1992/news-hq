package com.oddlycoder.newshq.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.oddlycoder.newshq.MainActivity
import com.oddlycoder.newshq.R
import com.oddlycoder.newshq.databinding.FragmentArticleDetailBinding
import com.oddlycoder.newshq.model.Article
import com.oddlycoder.newshq.viewmodel.ArticleDetailViewModel
import java.io.Serializable

class ArticleDetailFragment : Fragment() {

    private var _binding: FragmentArticleDetailBinding? = null
    private val binding get() = _binding!!

    private var article: Article? = null

    // detail view model
    private val articleDetailViewModel: ArticleDetailViewModel by lazy {
        ViewModelProvider(this).get(ArticleDetailViewModel::class.java)
    }

    companion object {
        private const val ARTICLE = "article"
        private const val TAG = "ArticleDetailFragment"
        // detail factory setup
        fun newInstance(article: Article): ArticleDetailFragment {
            val aArgs = Bundle()
            aArgs.putSerializable(ARTICLE, article)
            return ArticleDetailFragment().also { detail -> detail.arguments = aArgs }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleDetailBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        // restore article state
        if (savedInstanceState != null) {
            articleDetailViewModel.setArticle(savedInstanceState.getSerializable(ARTICLE) as Article)
        }

        // was article passed to fragment
        if (arguments != null) {
            val article = arguments?.getSerializable(ARTICLE)
            articleDetailViewModel.setArticle(article as Article)
        }

        binding.imageViewDetailPopBack.setOnClickListener(View.OnClickListener { v ->
            popBackFragment()
        })

        Log.d(TAG, "onCreateView: Image ${articleDetailViewModel.getArticle().url}")

        setupUI()
        return view
    }

    // persist article model
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(ARTICLE, articleDetailViewModel.getArticle())
    }

    private fun setupUI() {
        binding.toolBarDetailTitle.text = articleDetailViewModel.getArticle().title
        binding.textViewDetailTitle.text = articleDetailViewModel.getArticle().title
        binding.textViewDetailAuthor.text = articleDetailViewModel.getArticle().author
        binding.textViewDetailDescription.text = articleDetailViewModel.getArticle().text

        // load image from url
        Glide.with(this)
            .load(articleDetailViewModel.getArticle().url)
            .centerCrop()
            .placeholder(R.drawable.placeholder_gif)
            .into(binding.imageViewDetailImage)

    }

    private fun popBackFragment() {
        activity?.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        // destroy binding reference
        _binding = null
    }

}