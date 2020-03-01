package nadirbasalamah.android.com.numoapps.nutritionist.ui.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_nut_article.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.adapter.NutArticlePagerAdapter

class ArticleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nut_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val articlesPagerAdapter = NutArticlePagerAdapter(context,this.childFragmentManager)
        nut_article_view_pager.adapter = articlesPagerAdapter
        nut_article_tabs.setupWithViewPager(nut_article_view_pager)
    }
}