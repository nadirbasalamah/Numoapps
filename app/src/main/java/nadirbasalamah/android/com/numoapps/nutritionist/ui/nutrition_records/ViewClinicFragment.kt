package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records


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
    var idPatient: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_clinic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            NutritionistViewModel::class.java)
        nutritionistViewModel.setContext(context)
        nutritionistViewModel.getNutRecordById(idPatient)?.observe(this, Observer {result ->
            if(result?.status == true) {
                tv_view_tensi.setText(result.clinic_data.tensi.toString())
                tv_view_rr.setText(result.clinic_data.rr.toString())
                tv_view_data_lain.setText(result.clinic_data.lainnya)
                tv_view_oedema.setText(result.clinic_data.oedema.toString())
                tv_view_aktivitas.setText(result.clinic_data.aktivitas)
                tv_view_durasi_olahraga.setText(result.clinic_data.durasi_olahraga)
                tv_view_jenis_olahraga.setText(result.clinic_data.jenis_olahraga)
                tv_view_diagnosa_dahulu.setText(result.clinic_data.diagnosa_dahulu)
                tv_view_diagnosa_sekarang.setText(result.clinic_data.diagnosa_skrg)
            }
        })
    }
}