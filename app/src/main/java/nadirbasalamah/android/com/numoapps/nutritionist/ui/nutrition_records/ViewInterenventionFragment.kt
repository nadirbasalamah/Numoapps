package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_view_interenvention.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel

/**
 * A simple [Fragment] subclass.
 */
class ViewInterenventionFragment : Fragment() {
    private lateinit var nutritionistViewModel: NutritionistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_interenvention, container, false)
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
                tv_view_energi.setText(result.interenvention_data.energi.toString())
                tv_view_keterangan_inter.setText(result.interenvention_data.keterangan_inter)
                tv_view_persen_karbohidrat.setText(result.interenvention_data.persen_karbohidrat.toString())
                tv_view_gram_karbohidrat.setText(result.interenvention_data.gram_karbohidrat.toString())
                tv_view_persen_protein.setText(result.interenvention_data.persen_protein.toString())
                tv_view_gram_protein.setText(result.interenvention_data.gram_protein.toString())
                tv_view_persen_lemak.setText(result.interenvention_data.persen_lemak.toString())
                tv_view_gram_lemak.setText(result.interenvention_data.gram_lemak.toString())
            }
        })
    }
}