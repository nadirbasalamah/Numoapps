package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_diagnose.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel

/**
 * A simple [Fragment] subclass.
 */
class DiagnoseFragment : Fragment() {
    private lateinit var nutritionistViewModel: NutritionistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diagnose, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args:  DiagnoseFragmentArgs by navArgs()
        val patientId = args.IDPATIENT
        val mode = args.MODE

        if(mode.equals("EDIT_MODE")) {
            nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NutritionistViewModel::class.java)
            nutritionistViewModel.setContext(context)
            nutritionistViewModel.getNutRecordById(patientId)?.observe(viewLifecycleOwner, Observer {result ->
                if(result?.status == true) {
                    et_diagnosa.setText(result.diagnose_data.diagnose)
                }
            })
        }

        btn_diagnose_save.setOnClickListener {
            var data: HashMap<String, String> = HashMap<String, String>()
            val diagnose = et_diagnosa.text.toString()

            data.put("id",patientId.toString())
            data.put("diagnose",diagnose)

            nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NutritionistViewModel::class.java)
            nutritionistViewModel.setContext(context)
            nutritionistViewModel.updateDiagnose(data)?.observe(viewLifecycleOwner, Observer {result ->
                if(result?.status == true) {
                    Toast.makeText(context,"Perekaman data diagnosa berhasil", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}