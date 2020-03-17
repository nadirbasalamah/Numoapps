package nadirbasalamah.android.com.numoapps.patient.ui.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_articles.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.adapter.ArticlePagerAdapter

class ArticlesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val articlesPagerAdapter = ArticlePagerAdapter(context,this.childFragmentManager)
        articles_view_pager.adapter = articlesPagerAdapter
        articles_tabs.setupWithViewPager(articles_view_pager)
    }
}