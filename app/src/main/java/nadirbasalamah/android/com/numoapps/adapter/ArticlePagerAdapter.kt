package nadirbasalamah.android.com.numoapps.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.patient.ui.articles.ArticleListFragment
import nadirbasalamah.android.com.numoapps.patient.ui.articles.GuideListFragment

class ArticlePagerAdapter(private val mContext: Context?, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.title_articles, R.string.title_guide)
    private val pages = listOf(ArticleListFragment(), GuideListFragment())

    override fun getItem(position: Int): Fragment {
        return pages[position] as Fragment
    }

    override fun getCount(): Int = 2

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? = mContext?.resources?.getString(TAB_TITLES[position])

}