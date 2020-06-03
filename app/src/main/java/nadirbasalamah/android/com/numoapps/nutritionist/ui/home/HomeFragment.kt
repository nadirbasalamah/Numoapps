package nadirbasalamah.android.com.numoapps.nutritionist.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_nut_home2.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.adapter.NutPagerAdapter

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nut_home2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nutPagerAdapter = NutPagerAdapter(context,this.childFragmentManager)
        nut_view_pager.adapter = nutPagerAdapter
        nut_tabs.setupWithViewPager(nut_view_pager)
    }

}
