package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_clinic.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel

/**
 * A simple [Fragment] subclass.
 */
class ClinicFragment : Fragment() {
    private lateinit var nutritionistViewModel: NutritionistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clinic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appContext = AddNutritionRecordActivity.getAppContext()
        val patientData = appContext.getSharedPreferences("Patient_Data", Context.MODE_PRIVATE)
        val patientId = patientData.getInt("id_patient",0)
        val mode = patientData.getString("MODE","")

        if(mode.equals("EDIT_MODE")) {
            nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NutritionistViewModel::class.java)
            nutritionistViewModel.setContext(context)
            nutritionistViewModel.getNutRecordById(patientId)?.observe(viewLifecycleOwner, Observer {result ->
                if(result?.status == true) {
                    et_tensi.setText(result.clinic_data.tensi.toString())
                    et_rr.setText(result.clinic_data.rr.toString())
                    et_suhu.setText(result.clinic_data.suhu.toString())
                    et_data_lain.setText(result.clinic_data.lainnya)
                    et_oedema.setText(result.clinic_data.oedema.toString())
                    et_aktivitas.setText(result.clinic_data.aktivitas)
                    et_durasi_olahraga.setText(result.clinic_data.durasi_olahraga)
                    et_jenis_olahraga.setText(result.clinic_data.jenis_olahraga)
                    et_diagnosa_dahulu.setText(result.clinic_data.diagnosa_dahulu)
                    et_diagnosa_sekarang.setText(result.clinic_data.diagnosa_skrg)
                }
            })
        }

        btn_clinic_save.setOnClickListener {
            var data: HashMap<String, String> = HashMap<String, String>()
            val tensi = et_tensi.text.toString()
            val rr = et_rr.text.toString()
            val suhu = et_suhu.text.toString()
            val data_lain = et_data_lain.text.toString()
            val oedema = et_oedema.text.toString()
            val aktivitas = et_aktivitas.text.toString()
            val durasi_olahraga = et_durasi_olahraga.text.toString()
            val jenis_olahraga = et_jenis_olahraga.text.toString()
            val diagnosa_dahulu = et_diagnosa_dahulu.text.toString()
            val diagnosa_skrg = et_diagnosa_sekarang.text.toString()

            data.put("id",patientId.toString())
            data.put("tensi",tensi)
            data.put("rr",rr)
            data.put("suhu",suhu)
            data.put("lainnya",data_lain)
            data.put("oedema",oedema)
            data.put("aktivitas",aktivitas)
            data.put("durasi_olahraga",durasi_olahraga)
            data.put("jenis_olahraga",jenis_olahraga)
            data.put("diagnosa_dahulu",diagnosa_dahulu)
            data.put("diagnosa_skrg",diagnosa_skrg)

            nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NutritionistViewModel::class.java)
            nutritionistViewModel.setContext(context)
            nutritionistViewModel.updateClinic(data)?.observe(viewLifecycleOwner, Observer {result ->
                if(result?.status == true) {
                    Toast.makeText(context,"Perekaman data klinik berhasil", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}