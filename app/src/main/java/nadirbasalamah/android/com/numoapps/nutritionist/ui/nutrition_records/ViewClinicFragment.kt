package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_view_clinic.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel

/**
 * A simple [Fragment] subclass.
 */
class ViewClinicFragment : Fragment() {
    private lateinit var nutritionistViewModel: NutritionistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_clinic, container, false)
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
                tv_view_tensi.text = result.clinic_data.tensi.toString()
                tv_view_rr.text = result.clinic_data.rr.toString()
                tv_view_data_lain.text = result.clinic_data.lainnya
                tv_view_oedema.text = result.clinic_data.oedema.toString()
                tv_view_aktivitas.text = result.clinic_data.aktivitas
                tv_view_durasi_olahraga.text = result.clinic_data.durasi_olahraga
                tv_view_jenis_olahraga.text = result.clinic_data.jenis_olahraga
                tv_view_diagnosa_dahulu.text = result.clinic_data.diagnosa_dahulu
                tv_view_diagnosa_sekarang.text = result.clinic_data.diagnosa_skrg
            }
        })
    }
}