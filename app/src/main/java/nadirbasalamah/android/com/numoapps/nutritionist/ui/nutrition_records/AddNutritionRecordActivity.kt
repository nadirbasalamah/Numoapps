package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import nadirbasalamah.android.com.numoapps.R

class AddNutritionRecordActivity : AppCompatActivity() {
    private var patientId: Int = 0
    private var mode: String? = "MODE"
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var patientData: SharedPreferences
    private lateinit var patientName: TextView
    private var patientSampleName: String? = ""
    companion object {
        const val MODE = "MODE"
        const val PATIENT_ID = "PATIENT_ID"
        const val PATIENT_NAME = "PATIENT_NAME"
        private lateinit var appContext: Context
        fun getAppContext(): Context {
            return appContext
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_nutrition_record)

        patientId = intent.getIntExtra(PATIENT_ID,0)
        mode = intent.getStringExtra(MODE)
        patientSampleName = intent.getStringExtra(PATIENT_NAME)

        appContext = applicationContext
        patientData = appContext.getSharedPreferences("Patient_Data", MODE_PRIVATE)
        val patientDataEdit = patientData.edit()
        patientDataEdit.putInt("id_patient",patientId)
        patientDataEdit.putString("MODE",mode)
        patientDataEdit.apply()

        val drawerLayout: DrawerLayout = findViewById(R.id.enut_drawer_layout)
        val navView: NavigationView = findViewById(R.id.enut_nav_view)
        val navController = findNavController(R.id.enut_nav_host_fragment)

        patientName = navView.getHeaderView(0).findViewById(R.id.tv_patient_sample_name)
        patientName.text = patientSampleName

        navController.setGraph(R.navigation.edit_nutrecord_navigation)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration.Builder(
                R.id.enut_navigation_antropometry, R.id.enut_navigation_biochemistry,
                R.id.enut_navigation_clinic, R.id.enut_navigation_dietary,
                R.id.enut_navigation_diagnose, R.id.enut_navigation_interenvention,
                R.id.enut_navigation_monev,R.id.enut_navigation_foodmenu)
            .setDrawerLayout(drawerLayout)
            .build()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.enut_navigation_antropometry, R.id.enut_navigation_biochemistry,
                R.id.enut_navigation_clinic, R.id.enut_navigation_dietary,
                R.id.enut_navigation_diagnose, R.id.enut_navigation_interenvention,
                R.id.enut_navigation_monev,R.id.enut_navigation_foodmenu
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.enut_nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
