package nadirbasalamah.android.com.numoapps.patient.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import nadirbasalamah.android.com.numoapps.R

class PatientReminderSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_reminder_setting)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.setting_holder, ReminderSettingFragment())
            .commit()
    }
}