package nadirbasalamah.android.com.numoapps.admin.ui.patients

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import id.rizmaulana.sheenvalidator.lib.SheenValidator
import kotlinx.android.synthetic.main.activity_edit_patient.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.model.entity.Patient
import nadirbasalamah.android.com.numoapps.viewmodel.AdminViewModel

class EditPatientActivity : AppCompatActivity() {
    private lateinit var patient: Patient
    private lateinit var adminViewModel: AdminViewModel
    private lateinit var sheenValidator: SheenValidator
    companion object {
        const val EXTRA_PATIENT_DATA = "EXTRA_PATIENT_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_patient)
        sheenValidator = SheenValidator(this)

        patient = intent.getParcelableExtra(EXTRA_PATIENT_DATA) as Patient
        adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
        adminViewModel.setContext(applicationContext)
        adminViewModel.getPatientById(patient.id)?.observe(this, Observer {result ->
            if(result?.status == true) {
                et_patient_edit_address.setText(result.data.address)
                et_patient_edit_phone_number.setText(result.data.phone_number)
                et_patient_edit_edu.setText(result.data.education)
                et_patient_edit_job.setText(result.data.job)
                et_patient_edit_religion.setText(result.data.religion)
            }
        })

        sheenValidator.setOnValidatorListener {
            Toast.makeText(this,"Validasi sukses!",Toast.LENGTH_SHORT).show()
        }

        sheenValidator.registerAsRequired(et_patient_edit_address)
        sheenValidator.registerAsRequired(et_patient_edit_phone_number)
        sheenValidator.registerAsRequired(et_patient_edit_edu)
        sheenValidator.registerAsRequired(et_patient_edit_job)
        sheenValidator.registerAsRequired(et_patient_edit_religion)

        btn_patient_edit_save.setOnClickListener {
            sheenValidator.validate()
            val data: HashMap<String, String> = HashMap()
            val address = et_patient_edit_address.text.toString()
            val phone_number = et_patient_edit_phone_number.text.toString()
            val education = et_patient_edit_edu.text.toString()
            val job = et_patient_edit_job.text.toString()
            val religion = et_patient_edit_religion.text.toString()

            data.put("id",patient.id.toString())
            data.put("address",address)
            data.put("phone_number",phone_number)
            data.put("education",education)
            data.put("job",job)
            data.put("religion",religion)

            adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
            adminViewModel.setContext(applicationContext)
            adminViewModel.editPatient(data)?.observe(this, Observer {result ->
                if(result?.status == true) {
                    finish()
                }
            })
        }

    }
}
