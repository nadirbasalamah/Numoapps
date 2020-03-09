package nadirbasalamah.android.com.numoapps.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.admin.ui.nutritionists.NutritionistListFragment
import nadirbasalamah.android.com.numoapps.admin.ui.patients.PatientListFragment

class PeoplePagerAdapter(private val mContext: Context?, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.patient_title, R.string.nutritionist_title)
    private val pages = listOf(PatientListFragment(), NutritionistListFragment())

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int = 2

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? = mContext?.resources?.getString(TAB_TITLES[position])

}