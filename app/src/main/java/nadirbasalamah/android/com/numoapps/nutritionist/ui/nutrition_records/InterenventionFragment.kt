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
import kotlinx.android.synthetic.main.fragment_interenvention.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel

/**
 * A simple [Fragment] subclass.
 */
class InterenventionFragment : Fragment() {
    private lateinit var nutritionistViewModel: NutritionistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_interenvention, container, false)
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
                    et_energi.setText(result.interenvention_data.energi.toString())
                    et_keterangan_inter.setText(result.interenvention_data.keterangan_inter)
                    et_persen_karbohidrat.setText(result.interenvention_data.persen_karbohidrat.toString())
                    et_persen_protein.setText(result.interenvention_data.persen_protein.toString())
                    et_persen_lemak.setText(result.interenvention_data.persen_lemak.toString())
                }
            })
        }

        btn_interenvention_save.setOnClickListener {
            var data: HashMap<String, String> = HashMap<String, String>()
            val energi = et_energi.text.toString()
            val keterangan_inter = et_keterangan_inter.text.toString()
            val persen_karbohidrat = et_persen_karbohidrat.text.toString()
            val persen_protein = et_persen_protein.text.toString()
            val persen_lemak = et_persen_lemak.text.toString()


            data.put("id",patientId.toString())
            data.put("energi",energi)
            data.put("keterangan_inter",keterangan_inter)
            data.put("persen_karbohidrat",persen_karbohidrat)
            data.put("persen_protein",persen_protein)
            data.put("persen_lemak",persen_lemak)

            nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NutritionistViewModel::class.java)
            nutritionistViewModel.setContext(context)
            nutritionistViewModel.updateInterenvention(data)?.observe(viewLifecycleOwner, Observer {result ->
                if(result?.status == true) {
                    Toast.makeText(context,"Perekaman data interenvensi berhasil", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}