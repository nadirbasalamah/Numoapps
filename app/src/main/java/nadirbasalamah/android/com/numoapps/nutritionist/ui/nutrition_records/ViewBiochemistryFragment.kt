package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_view_biochemistry.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel

/**
 * A simple [Fragment] subclass.
 */
class ViewBiochemistryFragment : Fragment() {
    private lateinit var nutritionistViewModel: NutritionistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_biochemistry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appContext = ViewNutritionRecordActivity.getAppContext()
        val patientData = appContext.getSharedPreferences("Patient_Data", Context.MODE_PRIVATE)
        val patientId = patientData.getInt("id_patient",0)

        nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            NutritionistViewModel::class.java)
        nutritionistViewModel.setContext(context)
        nutritionistViewModel.getNutRecordById(patientId)?.observe(viewLifecycleOwner, Observer {result ->
            if(result?.status == true) {
                tv_view_gda.text = result.biochemistry_data.gda.toString()
                tv_view_gdp.text = result.biochemistry_data.gdp.toString()
                tv_view_gd2jpp.text = result.biochemistry_data.gd2jpp.toString()
                tv_view_asam_urat.text = result.biochemistry_data.asam_urat.toString()
                tv_view_trigliserida.text = result.biochemistry_data.trigliserida.toString()
                tv_view_kolesterol.text = result.biochemistry_data.kolesterol.toString()
                tv_view_ldl.text = result.biochemistry_data.ldl.toString()
                tv_view_hdl.text = result.biochemistry_data.hdl.toString()
                tv_view_ureum.text = result.biochemistry_data.ureum.toString()
                tv_view_kreatinin.text = result.biochemistry_data.kreatinin.toString()
                tv_view_sgot.text = result.biochemistry_data.sgot.toString()
                tv_view_sgpt.text = result.biochemistry_data.sgpt.toString()
            }
        })
    }
}