package nadirbasalamah.android.com.numoapps.nutritionist.ui.foodmenu


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_view_food_menu.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel

/**
 * A simple [Fragment] subclass.
 */
class ViewFoodMenuFragment : Fragment() {
    private lateinit var nutritionistViewModel: NutritionistViewModel
    var idPatient: Int? = 0
    companion object {
        const val EXTRA_ID_PATIENT = "EXTRA"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_food_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            NutritionistViewModel::class.java)
        nutritionistViewModel.setContext(context)
        nutritionistViewModel.getFoodMenuById(idPatient)?.observe(this, Observer {result ->
            if(result?.status == true) {
                tv_breakfast.text = result.data.breakfast
                tv_breakfast_time.text = result.data.breakfast_time
                tv_lunch.text = result.data.lunch
                tv_lunch_time.text = result.data.lunch_time
                tv_dinner.text = result.data.dinner
                tv_dinner_time.text = result.data.dinner_time
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            val patientId = arguments?.getInt(EXTRA_ID_PATIENT)
            idPatient = patientId
        }
    }
}