package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import nadirbasalamah.android.com.numoapps.R

class PatientDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NUT_PATIENT = "EXTRA_NUT_PATIENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nut_patient_detail)
    }
}
