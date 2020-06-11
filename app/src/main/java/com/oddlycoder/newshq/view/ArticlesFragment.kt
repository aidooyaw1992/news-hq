package com.oddlycoder.newshq.view


import android.content.Context
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

    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!

    private val articleViewModel: ArticlesViewModel by lazy {
        ViewModelProvider(this).get(ArticlesViewModel::class.java)
    }

    // recycler adapter
    private var adapter: ArticlesAdapter? = ArticlesAdapter(emptyList())
    private var articleCall: Boolean = false

    companion object {
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

        /** hide scroll end effect */
        binding.articlesRecyclerView.overScrollMode = View.OVER_SCROLL_NEVER
        binding.progressCircular.visibility = View.VISIBLE

        /** init recycler with empty list */
        binding.articlesRecyclerView.adapter = adapter

        //updateUI()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleViewModel.allArticles().observe(viewLifecycleOwner, Observer { liveArticles ->
            updateUI(liveArticles)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        /** set fragment callback to activity context */
        callbacks = context as FragmentCallbacks
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onStart() {
        super.onStart()
        articleViewModel.articleCall().observe(viewLifecycleOwner, Observer {failedCall ->
            if (failedCall) {
                binding.progressCircular.visibility = View.GONE
                articleViewModel.cachedArticles().observe(viewLifecycleOwner, Observer { cached ->
                    updateUI(cached)
                   Toast.makeText(context, "Showing bookmarked. Couldn't fetch articles", Toast.LENGTH_SHORT).show()
               })
            }
        })
    }

    private fun updateUI(articles: List<Article>) {
        adapter = ArticlesAdapter(articles)
        binding.articlesRecyclerView.adapter = adapter
        binding.progressCircular.visibility = View.GONE
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /**
     *  article fragment recycler view adapter
     */
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
            /** activity handles fragment transaction */
            callbacks?.onArticleSelected(article)
        }
    }

    /** delegate fragment call to activity */
    interface FragmentCallbacks {
        fun onArticleSelected(article: Article)
    }

    private var callbacks: FragmentCallbacks? = null
}

