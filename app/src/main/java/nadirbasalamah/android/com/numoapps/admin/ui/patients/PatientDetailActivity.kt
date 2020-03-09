package nadirbasalamah.android.com.numoapps.admin.ui.patients

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_patient_detail.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.model.entity.Patient
import nadirbasalamah.android.com.numoapps.viewmodel.AdminViewModel

class PatientDetailActivity : AppCompatActivity() {
    private lateinit var patient: Patient
    private lateinit var adminViewModel: AdminViewModel

    companion object {
        const val EXTRA_PATIENT = "EXTRA_PATIENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_detail)
        patient = intent?.getParcelableExtra(EXTRA_PATIENT) as Patient

        tv_detail_rm_number.setText(patient.rm_number)
        tv_detail_rm_nut_number.setText(patient.rmgizi_number)
        tv_detail_visitdate.setText(patient.visitdate)
        tv_detail_referral.setText(patient.referral)
        tv_detail_fullname.setText(patient.fullname)
        tv_detail_gender.setText(patient.gender)
        tv_detail_address.setText(patient.address)
        tv_detail_phone_number.setText(patient.phone_number)
        tv_detail_birthdate.setText(patient.birthdate)
        tv_detail_edu.setText(patient.education)
        tv_detail_job.setText(patient.job)
        tv_detail_religion.setText(patient.religion)

        btn_to_edit_patient.setOnClickListener {
            val intent = Intent(this,EditPatientActivity::class.java)
            intent.putExtra(EditPatientActivity.EXTRA_PATIENT_DATA,patient)
            startActivity(intent)
        }

        btn_delete_patient.setOnClickListener {
            adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
            adminViewModel.setContext(applicationContext)
            adminViewModel.deletePatient(patient.id)?.observe(this, Observer {result ->
                if(result?.status == true) {
                    finish()
                }
            })
        }
    }
}
