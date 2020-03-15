package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records


import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_monev.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel
import java.util.*
import kotlin.collections.HashMap


/**
 * A simple [Fragment] subclass.
 */
class MonevFragment : Fragment() {
    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0
    private var mon_date: String = ""
    private lateinit var nutritionistViewModel: NutritionistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monev, container, false)
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
                    et_mon_result.setText(result.monitoring_data.result)
                    tv_return_date.setText(result.monitoring_data.return_date)
                }
            })
        }

        btn_select_return_date.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                context!!.applicationContext,
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    tv_return_date.text = dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                }, year, month, day
            )

            datePickerDialog.show()
        }
        val calendar: Calendar = Calendar.getInstance()
        mon_date = calendar.get(Calendar.DAY_OF_MONTH).toString() + "-" + calendar.get(Calendar.MONTH).toString() + "-" + calendar.get(
            Calendar.YEAR)

        btn_monitoring_save.setOnClickListener {
            var data: HashMap<String, String> = HashMap<String, String>()
            val result = et_mon_result.text.toString()
            val return_date = tv_return_date.text.toString()


            data.put("id",patientId.toString())
            data.put("mon_date",mon_date)
            data.put("result",result)
            data.put("return_date",return_date)

            nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NutritionistViewModel::class.java)
            nutritionistViewModel.setContext(context)
            nutritionistViewModel.updateMonitoring(data)?.observe(viewLifecycleOwner, Observer {result ->
                if(result?.status == true) {
                    Toast.makeText(context,"Perekaman data monitoring berhasil", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}