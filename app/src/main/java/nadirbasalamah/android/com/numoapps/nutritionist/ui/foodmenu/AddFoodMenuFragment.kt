package nadirbasalamah.android.com.numoapps.nutritionist.ui.foodmenu


import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_add_food_menu.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records.AddNutritionRecordActivity
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel
import java.util.*
import kotlin.collections.HashMap


/**
 * A simple [Fragment] subclass.
 */
class AddFoodMenuFragment : Fragment() {
    private lateinit var nutritionistViewModel: NutritionistViewModel
    private var hour: Int = 0
    private var minute: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_food_menu, container, false)
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
            nutritionistViewModel.getFoodMenuById(patientId)?.observe(viewLifecycleOwner, Observer {result ->
                if(result?.status == true) {
                    et_breakfast.setText(result.data.breakfast)
                    tv_breakfast_time.setText(result.data.breakfast_time)
                    et_lunch.setText(result.data.lunch)
                    tv_lunch_time.setText(result.data.lunch_time)
                    et_dinner.setText(result.data.dinner)
                    tv_dinner_time.setText(result.data.dinner_time)
                }
            })
        }

        val calendar: Calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR_OF_DAY)
        minute = calendar.get(Calendar.MINUTE)

        btn_choose_breakfast_time.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                context, OnTimeSetListener { _, hourOfDay, minute -> tv_breakfast_time.setText("$hourOfDay:$minute") },
                hour,
                minute,
                true
            )
            timePickerDialog.show()
        }

        btn_choose_lunch_time.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                context, OnTimeSetListener { _, hourOfDay, minute -> tv_lunch_time.setText("$hourOfDay:$minute") },
                hour,
                minute,
                true
            )
            timePickerDialog.show()
        }

        btn_choose_dinner_time.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                context, OnTimeSetListener { _, hourOfDay, minute -> tv_dinner_time.setText("$hourOfDay:$minute") },
                hour,
                minute,
                true
            )
            timePickerDialog.show()
        }

        btn_foodmenu_save.setOnClickListener {
            var data: HashMap<String, String> = HashMap()
            val breakfast = et_breakfast.text.toString()
            val breakfast_time = tv_breakfast_time.text.toString()
            val lunch = et_lunch.text.toString()
            val lunch_time = tv_lunch_time.text.toString()
            val dinner = et_dinner.text.toString()
            val dinner_time = tv_dinner_time.text.toString()

            data.put("id",patientId.toString())
            data.put("breakfast",breakfast)
            data.put("breakfast_time",breakfast_time)
            data.put("lunch",lunch)
            data.put("lunch_time",lunch_time)
            data.put("dinner",dinner)
            data.put("dinner_time",dinner_time)

            nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NutritionistViewModel::class.java)
            nutritionistViewModel.setContext(context)
            if(mode.equals("EDIT_MODE")) {
                nutritionistViewModel.editFoodMenu(data)?.observe(viewLifecycleOwner, Observer {result ->
                    if(result?.status == true) {
                        Toast.makeText(context,"Perubahan data menu makanan berhasil", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                nutritionistViewModel.addFoodMenu(data)?.observe(viewLifecycleOwner, Observer {result ->
                    if(result?.status == true) {
                        Toast.makeText(context,"Penambahan data menu makanan berhasil", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}