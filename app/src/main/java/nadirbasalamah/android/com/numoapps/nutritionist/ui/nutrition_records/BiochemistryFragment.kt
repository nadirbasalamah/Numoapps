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
import kotlinx.android.synthetic.main.fragment_biochemistry.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel

/**
 * A simple [Fragment] subclass.
 */
class BiochemistryFragment : Fragment() {
    private lateinit var nutritionistViewModel: NutritionistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_biochemistry, container, false)
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
                    et_gda.setText(result.biochemistry_data.gda.toString())
                    et_gdp.setText(result.biochemistry_data.gdp.toString())
                    et_gd2jpp.setText(result.biochemistry_data.gd2jpp.toString())
                    et_asam_urat.setText(result.biochemistry_data.asam_urat.toString())
                    et_trigliserida.setText(result.biochemistry_data.trigliserida.toString())
                    et_kolesterol.setText(result.biochemistry_data.kolesterol.toString())
                    et_ldl.setText(result.biochemistry_data.ldl.toString())
                    et_hdl.setText(result.biochemistry_data.hdl.toString())
                    et_ureum.setText(result.biochemistry_data.ureum.toString())
                    et_kreatinin.setText(result.biochemistry_data.kreatinin.toString())
                    et_sgot.setText(result.biochemistry_data.sgot.toString())
                    et_sgpt.setText(result.biochemistry_data.sgpt.toString())
                }
            })
        }

        btn_biochemistry_save.setOnClickListener {
            val data: HashMap<String, String> = HashMap()
            val gda = et_gda.text.toString()
            val gdp = et_gdp.text.toString()
            val gd2jpp = et_gd2jpp.text.toString()
            val asam_urat = et_asam_urat.text.toString()
            val trigliserida = et_trigliserida.text.toString()
            val kolesterol = et_kolesterol.text.toString()
            val ldl = et_ldl.text.toString()
            val hdl = et_hdl.text.toString()
            val ureum = et_ureum.text.toString()
            val kreatinin = et_kreatinin.text.toString()
            val sgot = et_sgot.text.toString()
            val sgpt = et_sgpt.text.toString()

            data.put("id",patientId.toString())
            data.put("gda",gda)
            data.put("gdp",gdp)
            data.put("gd2jpp",gd2jpp)
            data.put("asam_urat",asam_urat)
            data.put("trigliserida",trigliserida)
            data.put("kolesterol",kolesterol)
            data.put("ldl",ldl)
            data.put("hdl",hdl)
            data.put("ureum",ureum)
            data.put("kreatinin",kreatinin)
            data.put("sgot",sgot)
            data.put("sgpt",sgpt)
            when {
                gda.isEmpty() -> et_gda.error = "Harap isi bidang ini!"
                gdp.isEmpty() ->  et_gdp.error = "Harap isi bidang ini!"
                gd2jpp.isEmpty() -> et_gd2jpp.error = "Harap isi bidang ini!"
                asam_urat.isEmpty() ->  et_asam_urat.error = "Harap isi bidang ini!"
                trigliserida.isEmpty() ->  et_trigliserida.error = "Harap isi bidang ini!"
                kolesterol.isEmpty() ->  et_kolesterol.error = "Harap isi bidang ini!"
                ldl.isEmpty() ->  et_ldl.error = "Harap isi bidang ini!"
                hdl.isEmpty() ->  et_hdl.error = "Harap isi bidang ini!"
                ureum.isEmpty() ->  et_ureum.error = "Harap isi bidang ini!"
                kreatinin.isEmpty() ->  et_kreatinin.error = "Harap isi bidang ini!"
                sgot.isEmpty() ->  et_sgot.error = "Harap isi bidang ini!"
                sgpt.isEmpty() ->  et_sgpt.error = "Harap isi bidang ini!"
                else -> {
                    nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NutritionistViewModel::class.java)
                    nutritionistViewModel.setContext(context)
                    nutritionistViewModel.updateBiochemistry(data)?.observe(viewLifecycleOwner, Observer {result ->
                        if(result?.status == true) {
                            Toast.makeText(context,"Perekaman data biokimia berhasil", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        }
    }
}