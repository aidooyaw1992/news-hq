package com.oddlycoder.newshq.view


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oddlycoder.newshq.MainActivity
import com.oddlycoder.newshq.R
import com.oddlycoder.newshq.databinding.FragmentArticlesBinding
import com.oddlycoder.newshq.model.Article
import com.oddlycoder.newshq.viewmodel.ArticlesViewModel
import java.io.Serializable

class ArticlesFragment : Fragment() {

    // handle view binding reference
    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!

    // view model init
    private val articleViewModel: ArticlesViewModel by lazy {
        ViewModelProvider(this).get(ArticlesViewModel::class.java)
    }

    // recycler adapter
    private var adapter: ArticlesAdapter? = null

    companion object {
        // setup fragment factory
        fun newInstance(): ArticlesFragment {
            return ArticlesFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticlesBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        binding.articlesRecyclerView.layoutManager = LinearLayoutManager(context)

        // hide scroll end effect
        binding.articlesRecyclerView.overScrollMode = View.OVER_SCROLL_NEVER
        binding.progressCircular.visibility = View.VISIBLE

        // initialize recycler view and articles
        updateUI()
        return view
    }

    private fun updateUI() {
        articleViewModel.allArticles().observe(this, Observer { liveArticles ->
            adapter = ArticlesAdapter(liveArticles)
            binding.articlesRecyclerView.adapter = adapter
            binding.progressCircular.visibility = View.GONE
            // reload recycler if data is retrieve
            adapter?.notifyDataSetChanged()
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        // clean up binding reference
        _binding = null
    }

    private inner class ArticlesAdapter(var articles: List<Article>) :
        RecyclerView.Adapter<ArticleViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
            val view = layoutInflater.inflate(R.layout.list_item_article, parent, false)
            return ArticleViewHolder(view)
        }

        override fun getItemCount(): Int {
            return articles.size
        }

        override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
            val article = articles[position]
            // set article result to views
            holder.bind(article)
        }
    }

    private inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private lateinit var article: Article

        private val titleTextView = itemView.findViewById<TextView>(R.id.list_item_title)
        private val authorTextView = itemView.findViewById<TextView>(R.id.list_item_author)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(article: Article) {
            this.article = article
            titleTextView.text = article.title
            authorTextView.text = article.author
        }

        override fun onClick(v: View?) {
            // newInstance factory sets up detail
            val fragment = ArticleDetailFragment.newInstance(article)
            val fm = activity?.supportFragmentManager
            fm?.beginTransaction()
                ?.replace(R.id.fragment_container, fragment)
                ?.addToBackStack("detail_fragment")
                ?.setCustomAnimations(R.anim.enter_right, R.anim.exit_left)
                ?.commit()
        }
    }
}

