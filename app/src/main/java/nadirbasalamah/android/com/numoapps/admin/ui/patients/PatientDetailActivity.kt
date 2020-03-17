package nadirbasalamah.android.com.numoapps.admin.ui.patients

import android.app.AlertDialog
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

        tv_detail_rm_number.text = patient.rm_number
        tv_detail_rm_nut_number.text = patient.rmgizi_number
        tv_detail_visitdate.text = patient.visitdate
        tv_detail_referral.text = patient.referral
        tv_detail_fullname.text = patient.fullname
        tv_detail_age.text = patient.age.toString()
        tv_detail_gender.text = patient.gender
        tv_detail_address.text = patient.address
        tv_detail_phone_number.text = patient.phone_number
        tv_detail_birthdate.text = patient.birthdate
        tv_detail_edu.text = patient.education
        tv_detail_job.text = patient.job
        tv_detail_religion.text = patient.religion

        btn_to_edit_patient.setOnClickListener {
            val intent = Intent(this,EditPatientActivity::class.java)
            intent.putExtra(EditPatientActivity.EXTRA_PATIENT_DATA,patient)
            startActivity(intent)
        }

        btn_delete_patient.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage(R.string.delete_confirmation)
                .setPositiveButton(R.string.confirmation_yes
                ) { dialog, _ ->
                    adminViewModel =
                        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                            AdminViewModel::class.java
                        )
                    adminViewModel.setContext(applicationContext)
                    adminViewModel.deletePatient(patient.id)?.observe(this, Observer { result ->
                        if (result?.status == true) {
                            finish()
                        }
                    })
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.confirmation_no
                ) { dialog, _ ->
                    dialog.cancel()
                }
            // Create the AlertDialog object and return it
            builder.create()
            builder.show()
        }
    }
}