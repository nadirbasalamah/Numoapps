package nadirbasalamah.android.com.numoapps.admin.ui.patients

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.rizmaulana.sheenvalidator.lib.SheenValidator
import kotlinx.android.synthetic.main.activity_add_patient.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.AdminViewModel
import java.util.*

class AddPatientActivity : AppCompatActivity() {
    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0
    private var age: Int = 0
    private var gender: String = ""
    private var visitdate: String = ""
    private lateinit var adminViewModel: AdminViewModel
    private lateinit var sheenValidator: SheenValidator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_patient)
        sheenValidator = SheenValidator(this)

        btn_birthdate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    tv_patient_birthdate.text =
                        dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                    age = calendar.get(Calendar.YEAR) - year
                }, year, month, day
            )
            datePickerDialog.show()
        }
        val calendar: Calendar = Calendar.getInstance()
        visitdate = calendar.get(Calendar.DAY_OF_MONTH).toString() + "-" + calendar.get(Calendar.MONTH).toString() + "-" + calendar.get(Calendar.YEAR)

        rg_patient_gender.setOnCheckedChangeListener{ _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            gender = radio.text.toString()
        }

        sheenValidator.setOnValidatorListener {
            Toast.makeText(this,"Validasi sukses!",Toast.LENGTH_SHORT).show()
        }

        sheenValidator.registerAsRequired(et_rm_number)
        sheenValidator.registerAsRequired(et_rmnut_number)
        sheenValidator.registerAsRequired(et_referral)
        sheenValidator.registerAsRequired(et_patient_fullname)
        sheenValidator.registerAsRequired(et_patient_address)
        sheenValidator.registerAsRequired(et_patient_phone_number)
        sheenValidator.registerAsRequired(et_patient_edu)
        sheenValidator.registerAsRequired(et_patient_job)
        sheenValidator.registerAsRequired(et_patient_religion)

        btn_patient_save.setOnClickListener {
            sheenValidator.validate()
            val data : HashMap<String, String> = HashMap<String, String> ()
            val rm_number = et_rm_number.text.toString()
            val rmgizi_number = et_rmnut_number.text.toString()
            val visitdate = visitdate
            val referral = et_referral.text.toString()
            val fullname = et_patient_fullname.text.toString()
            val age = age.toString()
            val gender = gender
            val address = et_patient_address.text.toString()
            val phone_number = et_patient_phone_number.text.toString()
            val birthdate = tv_patient_birthdate.text.toString()
            val education = et_patient_edu.text.toString()
            val job = et_patient_job.text.toString()
            val religion = et_patient_religion.text.toString()

            data.put("rm_number",rm_number)
            data.put("rmgizi_number",rmgizi_number)
            data.put("visitdate",visitdate)
            data.put("referral",referral)
            data.put("fullname",fullname)
            data.put("age",age)
            data.put("gender",gender)
            data.put("address",address)
            data.put("phone_number",phone_number)
            data.put("birthdate",birthdate)
            data.put("education",education)
            data.put("job",job)
            data.put("religion",religion)

            adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
            adminViewModel.setContext(applicationContext)
            adminViewModel.addPatient(data)?.observe(this, Observer {result ->
                if(result?.status == true) {
                    finish()
                }
            })
        }
    }
}
