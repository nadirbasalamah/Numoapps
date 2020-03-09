package nadirbasalamah.android.com.numoapps.nutritionist.ui.articles


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_nut_guide_list.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.adapter.ArticlesAdapter
import nadirbasalamah.android.com.numoapps.model.entity.Article
import nadirbasalamah.android.com.numoapps.viewmodel.ArticleViewModel

/**
 * A simple [Fragment] subclass.
 */
class NutGuideListFragment : Fragment() {
    private lateinit var adapter: ArticlesAdapter
    private lateinit var articleViewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nut_guide_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_nut_guidelist.setHasFixedSize(true)
        showGuideList()
        fab_add_guide.setOnClickListener {
            val intent = Intent(context,AddArticleActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showGuideList() {
        rv_nut_guidelist.layoutManager = LinearLayoutManager(context)
        adapter = ArticlesAdapter()
        adapter.notifyDataSetChanged()
        rv_nut_guidelist.adapter = adapter

        articleViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ArticleViewModel::class.java)
        articleViewModel.setContext(context)

        showLoading(true)

        articleViewModel.getAllGuides()?.observe(this, Observer {articleItems ->
            if(articleItems != null) {
                adapter.setData(articleItems.data)
                showLoading(false)
            }
        })

        adapter.setOnItemClickCallback(object : ArticlesAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Article) {
                val detailArticleIntent = Intent(context, NutArticleDetailActivity::class.java)
                startActivity(detailArticleIntent)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if(state) {
            pb_nut_guidelist.visibility = View.VISIBLE
        } else {
            pb_nut_guidelist.visibility = View.GONE
        }
    }
}
