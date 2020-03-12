package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_antropometry.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel

/**
 * A simple [Fragment] subclass.
 */
class AntropometryFragment : Fragment() {
    private lateinit var nutritionistViewModel: NutritionistViewModel
    var idPatient: Int? = 0
    var mode: String? = ""

    companion object {
        const val EXTRA_ID_PATIENT = "EXTRA_ID_PATIENT"
        const val EXTRA_MODE = "EXTRA_MODE"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_antropometry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(mode.equals("EDIT_MODE")) {
            nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NutritionistViewModel::class.java)
            nutritionistViewModel.setContext(context)
            nutritionistViewModel.getNutRecordById(idPatient)?.observe(this, Observer {result ->
                if(result?.status == true) {
                    et_body_weight.setText(result.antropometry_data.bb.toString())
                    et_body_height.setText(result.antropometry_data.tb.toString())
                    et_head_circle.setText(result.antropometry_data.lila.toString())
                    et_body_weight_index.setText(result.antropometry_data.bbi.toString())
                    et_fat.setText(result.antropometry_data.fat.toString())
                    et_visceral_fat.setText(result.antropometry_data.visceral_fat.toString())
                    et_muscle.setText(result.antropometry_data.muscle.toString())
                    et_body_age.setText(result.antropometry_data.body_age.toString())
                }
            })
        }

        btn_antropometry_save.setOnClickListener {
            var data: HashMap<String, String> = HashMap<String, String>()
            val bb = et_body_weight.text.toString()
            val tb = et_body_height.text.toString()
            val lila = et_head_circle.text.toString()
            val bbi = et_body_weight_index.text.toString()
            val fat = et_fat.text.toString()
            val visceral_fat = et_visceral_fat.text.toString()
            val muscle = et_muscle.text.toString()
            val body_age = et_body_age.text.toString()

            data.put("id",idPatient.toString())
            data.put("bb",bb)
            data.put("tb",tb)
            data.put("lila",lila)
            data.put("bbi",bbi)
            data.put("fat",fat)
            data.put("visceral_fat",visceral_fat)
            data.put("muscle",muscle)
            data.put("body_age",body_age)

            nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NutritionistViewModel::class.java)
            nutritionistViewModel.setContext(context)
            nutritionistViewModel.updateAntropometry(data)?.observe(this, Observer {result ->
                if(result?.status == true) {
                    Toast.makeText(context,"Perekaman data antropometri berhasil",Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            val patientId = arguments?.getInt(EXTRA_ID_PATIENT)
            val dataMode = arguments?.getString(EXTRA_MODE)
            idPatient = patientId
            mode = dataMode
        }
    }
}