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
import java.util.*

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

        btn_to_edit_patient.setOnClickListener {
            val intent = Intent(this,EditPatientActivity::class.java)
            intent.putExtra(EditPatientActivity.EXTRA_PATIENT_DATA,patient)
            startActivity(intent)
        }

        btn_register_patient.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage(R.string.register_confirmation)
                .setPositiveButton(R.string.confirmation_yes
                ) { dialog, _ ->
                    adminViewModel =
                        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                            AdminViewModel::class.java
                        )
                    adminViewModel.setContext(applicationContext)
                    val data : HashMap<String, String> = HashMap<String, String> ()
                    val calendar: Calendar = Calendar.getInstance()
                    val visitdate = calendar.get(Calendar.DAY_OF_MONTH).toString() + "-" + calendar.get(Calendar.MONTH).toString() + "-" + calendar.get(Calendar.YEAR)
                    data.put("id",patient.id.toString())
                    data.put("visitdate",visitdate)
                    adminViewModel.registerPatient(data)?.observe(this, Observer { result ->
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