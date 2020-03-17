package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_view_dietary.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel

/**
 * A simple [Fragment] subclass.
 */
class ViewDietaryFragment : Fragment() {
    private lateinit var nutritionistViewModel: NutritionistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_dietary, container, false)
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
                tv_view_nafsu_makan.text = result.dietary_data.nafsu_makan
                tv_view_frekuensi_makan.text = result.dietary_data.frekuensi_makan
                tv_view_alergi.text = result.dietary_data.alergi
                tv_view_makanan_kesukaan.text = result.dietary_data.makanan_kesukaan
                tv_view_dietary_nasi.text = result.dietary_data.dietary_nasi
                tv_view_dietary_lauk_hewani.text = result.dietary_data.dietary_lauk_hewani
                tv_view_dietary_lauk_nabati.text = result.dietary_data.dietary_lauk_nabati
                tv_view_dietary_sayur.text = result.dietary_data.dietary_sayur
                tv_view_dietary_sumber_minyak.text = result.dietary_data.dietary_sumber_minyak
                tv_view_dietary_minuman.text = result.dietary_data.dietary_minuman
                tv_view_dietary_soft_drink.text = result.dietary_data.dietary_softdrink
                tv_view_dietary_jus.text = result.dietary_data.dietary_jus
                tv_view_dietary_suplemen.text = result.dietary_data.dietary_suplemen
                tv_view_dietary_lain.text = result.dietary_data.dietary_lainnya
                tv_view_dlain2.text = result.dietary_data.lain_lain
            }
        })
    }
}