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
import id.rizmaulana.sheenvalidator.lib.SheenValidator
import kotlinx.android.synthetic.main.fragment_dietary.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel

/**
 * A simple [Fragment] subclass.
 */
class DietaryFragment : Fragment() {
    private lateinit var nutritionistViewModel: NutritionistViewModel
    private lateinit var sheenValidator: SheenValidator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dietary, container, false)
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
                    et_nafsu_makan.setText(result.dietary_data.nafsu_makan)
                    et_frekuensi_makan.setText(result.dietary_data.frekuensi_makan)
                    et_alergi.setText(result.dietary_data.alergi)
                    et_makanan_kesukaan.setText(result.dietary_data.makanan_kesukaan)
                    et_dietary_nasi.setText(result.dietary_data.dietary_nasi)
                    et_dietary_lauk_hewani.setText(result.dietary_data.dietary_lauk_hewani)
                    et_dietary_lauk_nabati.setText(result.dietary_data.dietary_lauk_nabati)
                    et_dietary_sayur.setText(result.dietary_data.dietary_sayur)
                    et_dietary_sumber_minyak.setText(result.dietary_data.dietary_sumber_minyak)
                    et_dietary_minuman.setText(result.dietary_data.dietary_minuman)
                    et_softdrink.setText(result.dietary_data.dietary_softdrink)
                    et_jus.setText(result.dietary_data.dietary_jus)
                    et_suplemen.setText(result.dietary_data.dietary_suplemen)
                    et_dlain.setText(result.dietary_data.dietary_lainnya)
                    et_dlain2.setText(result.dietary_data.lain_lain)
                }
            })
        }

        sheenValidator = SheenValidator(requireContext())
        sheenValidator.setOnValidatorListener {
            Toast.makeText(context,"Validasi sukses!",Toast.LENGTH_SHORT).show()
        }
        sheenValidator.registerAsRequired(et_nafsu_makan)
        sheenValidator.registerAsRequired(et_frekuensi_makan)
        sheenValidator.registerAsRequired(et_alergi)
        sheenValidator.registerAsRequired(et_makanan_kesukaan)
        sheenValidator.registerAsRequired(et_dietary_nasi)
        sheenValidator.registerAsRequired(et_dietary_lauk_hewani)
        sheenValidator.registerAsRequired(et_dietary_lauk_nabati)
        sheenValidator.registerAsRequired(et_dietary_sayur)
        sheenValidator.registerAsRequired(et_dietary_sumber_minyak)
        sheenValidator.registerAsRequired(et_dietary_minuman)
        sheenValidator.registerAsRequired(et_softdrink)
        sheenValidator.registerAsRequired(et_jus)
        sheenValidator.registerAsRequired(et_suplemen)
        sheenValidator.registerAsRequired(et_dlain)
        sheenValidator.registerAsRequired(et_dlain2)

        btn_dietary_save.setOnClickListener {
            sheenValidator.validate()
            val data: HashMap<String, String> = HashMap()
            val nafsu_makan = et_nafsu_makan.text.toString()
            val frekuensi_makan = et_frekuensi_makan.text.toString()
            val alergi = et_alergi.text.toString()
            val makanan_kesukaan = et_makanan_kesukaan.text.toString()
            val dietary_nasi = et_dietary_nasi.text.toString()
            val dietary_lauk_hewani = et_dietary_lauk_hewani.text.toString()
            val dietary_lauk_nabati = et_dietary_lauk_nabati.text.toString()
            val dietary_sayur = et_dietary_sayur.text.toString()
            val dietary_sumber_minyak = et_dietary_sumber_minyak.text.toString()
            val dietary_minuman = et_dietary_minuman.text.toString()
            val dietary_softdrink = et_softdrink.text.toString()
            val dietary_jus = et_jus.text.toString()
            val dietary_suplemen = et_suplemen.text.toString()
            val dietary_lainnya = et_dlain.text.toString()
            val lain_lain = et_dlain2.text.toString()

            data.put("id",patientId.toString())
            data.put("nafsu_makan",nafsu_makan)
            data.put("frekuensi_makan",frekuensi_makan)
            data.put("alergi",alergi)
            data.put("makanan_kesukaan",makanan_kesukaan)
            data.put("dietary_nasi",dietary_nasi)
            data.put("dietary_lauk_hewani",dietary_lauk_hewani)
            data.put("dietary_lauk_nabati",dietary_lauk_nabati)
            data.put("dietary_sayur",dietary_sayur)
            data.put("dietary_sumber_minyak",dietary_sumber_minyak)
            data.put("dietary_minuman",dietary_minuman)
            data.put("dietary_softdrink",dietary_softdrink)
            data.put("dietary_jus",dietary_jus)
            data.put("dietary_suplemen",dietary_suplemen)
            data.put("dietary_lainnya",dietary_lainnya)
            data.put("lain_lain",lain_lain)

            nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NutritionistViewModel::class.java)
            nutritionistViewModel.setContext(context)
            nutritionistViewModel.updateDietary(data)?.observe(viewLifecycleOwner, Observer {result ->
                if(result?.status == true) {
                    Toast.makeText(context,"Perekaman data dietary berhasil", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}