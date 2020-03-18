package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_nut_patient_detail.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.main.MainActivity
import nadirbasalamah.android.com.numoapps.model.entity.Patient
import nadirbasalamah.android.com.numoapps.viewmodel.AdminViewModel
import nadirbasalamah.android.com.numoapps.viewmodel.NutritionistViewModel

class PatientDetailActivity : AppCompatActivity() {
    private lateinit var patient: Patient
    private lateinit var nutritionistViewModel: NutritionistViewModel
    private lateinit var adminViewModel: AdminViewModel
    private var userId: Int = 0

    companion object {
        const val EXTRA_NUT_PATIENT = "EXTRA_NUT_PATIENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nut_patient_detail)

        val appContext = MainActivity.getAppContext()
        val loginData = appContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        userId = loginData.getInt("id_user",0)

        patient = intent.getParcelableExtra(EXTRA_NUT_PATIENT) as Patient
        adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
        adminViewModel.setContext(applicationContext)
        adminViewModel.getPatientById(patient.id)?.observe(this, Observer { result ->
            if (result?.status == true) {
                tv_detail_rm_number.text = result.data.rm_number
                tv_detail_rm_nut_number.text = result.data.rmgizi_number
                tv_detail_visitdate.text = result.data.visitdate
                tv_detail_referral.text = result.data.referral
                tv_detail_fullname.text = result.data.fullname
                tv_detail_age.text = result.data.age.toString()
                tv_detail_gender.text = result.data.gender
                tv_detail_address.text = result.data.address
                tv_detail_phone_number.text = result.data.phone_number
                tv_detail_birthdate.text = result.data.birthdate
                tv_detail_edu.text = result.data.education
                tv_detail_job.text = result.data.job
                tv_detail_religion.text = result.data.religion
            }
        })

        nutritionistViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(NutritionistViewModel::class.java)
        nutritionistViewModel.setContext(applicationContext)

        nutritionistViewModel.getNutRecordById(patient.id)?.observe(this, Observer {nutRecord ->
            if(nutRecord?.status == true) {
                btn_to_add_nutrecord.isEnabled = false
                btn_to_add_nutrecord.background = R.color.colorPrimaryDisabled.toDrawable()
            } else {
                btn_to_view_nutrecord.isEnabled = false
                btn_to_view_nutrecord.background = R.color.colorPrimaryDisabled.toDrawable()
            }
        })

        btn_to_add_nutrecord.setOnClickListener {
            val data: HashMap<String, String> = HashMap()
            val idPatient = patient.id
            val idNutritionist = userId

            data.put("id",idPatient.toString())
            data.put("id_nutritionist",idNutritionist.toString())

            nutritionistViewModel.setNutritionist(data)?.observe(this, Observer {result ->
                if(result?.status == true) {
                    val intent = Intent(applicationContext,AddNutritionRecordActivity::class.java)
                    intent.putExtra(AddNutritionRecordActivity.PATIENT_ID,patient.id)
                    intent.putExtra(AddNutritionRecordActivity.MODE,"ADD_MODE")
                    intent.putExtra(AddNutritionRecordActivity.PATIENT_NAME,tv_detail_fullname.text)
                    startActivity(intent)
                }
            })
        }

        btn_to_view_nutrecord.setOnClickListener {
            val intent = Intent(applicationContext,ViewNutritionRecordActivity::class.java)
            intent.putExtra(ViewNutritionRecordActivity.PATIENT_ID,patient.id)
            intent.putExtra(ViewNutritionRecordActivity.PATIENT_NAME,tv_detail_fullname.text)
            startActivity(intent)
        }

    }
}
