package nadirbasalamah.android.com.numoapps.admin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_admin_home.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.adapter.PeoplePagerAdapter

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_admin_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val peoplePagerAdapter = PeoplePagerAdapter(context,this.childFragmentManager)
        admin_view_pager.adapter = peoplePagerAdapter
        admin_tabs.setupWithViewPager(admin_view_pager)
    }
}