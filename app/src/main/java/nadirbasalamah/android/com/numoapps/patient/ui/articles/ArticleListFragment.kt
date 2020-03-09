package nadirbasalamah.android.com.numoapps.patient.ui.articles


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_article_list.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.adapter.ArticlesAdapter
import nadirbasalamah.android.com.numoapps.model.entity.Article
import nadirbasalamah.android.com.numoapps.viewmodel.ArticleViewModel

/**
 * A simple [Fragment] subclass.
 */
class ArticleListFragment : Fragment() {
    private lateinit var adapter: ArticlesAdapter
    private lateinit var articleViewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_articles.setHasFixedSize(true)
        showArticleList()
    }

    private fun showArticleList() {
        rv_articles.layoutManager = LinearLayoutManager(context)
        adapter = ArticlesAdapter()
        adapter.notifyDataSetChanged()
        rv_articles.adapter = adapter

        articleViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ArticleViewModel::class.java)
        articleViewModel.setContext(context)

        showLoading(true)

        articleViewModel.getAllArticles()?.observe(this, Observer {articleItems ->
            if(articleItems != null) {
                adapter.setData(articleItems.data)
                showLoading(false)
            }
        })

        adapter.setOnItemClickCallback(object : ArticlesAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Article) {
                val detailArticleIntent = Intent(context, ArticleDetailActivity::class.java)
                detailArticleIntent.putExtra(ArticleDetailActivity.EXTRA_ARTICLE,data)
                startActivity(detailArticleIntent)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if(state) {
            pb_articlelist.visibility = View.VISIBLE
        } else {
            pb_articlelist.visibility = View.GONE
        }
    }
}
