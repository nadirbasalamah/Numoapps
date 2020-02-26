package nadirbasalamah.android.com.numoapps.admin.ui.nutritionists


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import nadirbasalamah.android.com.numoapps.R

/**
 * A simple [Fragment] subclass.
 */
class NutritionistListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nutritionist_list, container, false)
    }


}
