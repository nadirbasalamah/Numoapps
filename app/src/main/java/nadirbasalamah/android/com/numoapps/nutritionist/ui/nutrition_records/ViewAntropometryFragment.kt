package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_view_antropometry.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel

/**
 * A simple [Fragment] subclass.
 */
class ViewAntropometryFragment : Fragment() {
    private lateinit var nutritionistViewModel: NutritionistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_antropometry, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val patientId = arguments?.getInt("IDPATIENT")
        val appContext = ViewNutritionRecordActivity.getAppContext()
        val patientData = appContext.getSharedPreferences("Patient_Data", Context.MODE_PRIVATE)
        val patientId = patientData.getInt("id_patient",0)

        nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NutritionistViewModel::class.java)
        nutritionistViewModel.setContext(context)
        nutritionistViewModel.getNutRecordById(patientId)?.observe(viewLifecycleOwner, Observer {result ->
            if(result?.status == true) {
                tv_view_bw.text = result.antropometry_data.bb.toString()
                tv_view_bh.text = result.antropometry_data.tb.toString()
                tv_view_hc.text = result.antropometry_data.lila.toString()
                tv_view_imt.text = result.antropometry_data.imt.toString()
                tv_view_bbi.text = result.antropometry_data.bbi.toString()
                tv_view_status.text = result.antropometry_data.status
                tv_view_fat.text = result.antropometry_data.fat.toString()
                tv_view_visceral_fat.text = result.antropometry_data.visceral_fat.toString()
                tv_view_muscle.text = result.antropometry_data.muscle.toString()
                tv_view_body_age.text = result.antropometry_data.body_age.toString()
            }
        })
    }
}