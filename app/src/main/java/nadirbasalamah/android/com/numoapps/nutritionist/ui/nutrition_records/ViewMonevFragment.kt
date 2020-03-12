package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_view_monev.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel

/**
 * A simple [Fragment] subclass.
 */
class ViewMonevFragment : Fragment() {
    private lateinit var nutritionistViewModel: NutritionistViewModel
    var idPatient: Int? = 0
    companion object {
        const val EXTRA_ID_PATIENT = "ID_PATIENT"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_monev, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            NutritionistViewModel::class.java)
        nutritionistViewModel.setContext(context)
        nutritionistViewModel.getNutRecordById(idPatient)?.observe(this, Observer {result ->
            if(result?.status == true) {
                tv_view_mondate.text = result.monitoring_data.mon_date
                tv_view_monresult.text = result.monitoring_data.result
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