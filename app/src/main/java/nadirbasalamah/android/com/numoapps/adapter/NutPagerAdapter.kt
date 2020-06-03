package nadirbasalamah.android.com.numoapps.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.nutritionist.ui.home.PatientVisitFragment
import nadirbasalamah.android.com.numoapps.nutritionist.ui.home.PatientsFragment

class NutPagerAdapter(private val mContext: Context?, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.patient_title, R.string.patient_visit_title)
    private val pages = listOf(PatientsFragment(), PatientVisitFragment())

    override fun getItem(position: Int): Fragment {
        return pages[position] as Fragment
    }

    override fun getCount(): Int = 2

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? = mContext?.resources?.getString(TAB_TITLES[position])
}