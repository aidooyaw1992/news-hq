package com.oddlycoder.newshq.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.oddlycoder.newshq.databinding.FragmentArticleDetailBinding
import com.oddlycoder.newshq.viewmodel.ArticleDetailViewModel

class ArticleDetailFragment : Fragment() {

    private var _binding: FragmentArticleDetailBinding? = null
    private val binding get() = _binding!!

    // detail view model
    private val articleDetailViewModel: ArticleDetailViewModel by lazy {
        ViewModelProvider(this).get(ArticleDetailViewModel::class.java)
    }

    companion object {
        // singleton instance
        fun newInstance(): ArticleDetailFragment = ArticleDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleDetailBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        // destroy binding reference
        _binding = null
    }

}