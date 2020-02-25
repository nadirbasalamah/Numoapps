package nadirbasalamah.android.com.numoapps.patient.ui.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_articles.*
import nadirbasalamah.android.com.numoapps.R

class ArticlesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val favoritesPagerAdapter = FavoritesPagerAdapter(context,this.childFragmentManager)
//        view_pager.adapter = favoritesPagerAdapter
//        tabs.setupWithViewPager(view_pager)
//    }
}