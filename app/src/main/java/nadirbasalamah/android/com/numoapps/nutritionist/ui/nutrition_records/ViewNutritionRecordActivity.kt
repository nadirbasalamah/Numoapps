package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import nadirbasalamah.android.com.numoapps.R

class ViewNutritionRecordActivity : AppCompatActivity() {
    private var patientId: Int = 0
    private lateinit var appBarConfiguration: AppBarConfiguration
    companion object {
        const val PATIENT_ID = "PATIENT_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_nutrition_record)

        patientId = intent.getIntExtra(PATIENT_ID,0)

        val antropometryFragment = ViewAntropometryFragment()
        val biochemistryFragment = ViewBiochemistryFragment()
        val clinicFragment = ViewClinicFragment()
        val dietaryFragment  = ViewDietaryFragment()
        val diagnoseFragment = ViewDiagnoseFragment()
        val interenventionFragment = ViewInterenventionFragment()
        val monevFragment = ViewMonevFragment()

        Toast.makeText(applicationContext,"ID DARI ACTIVITY : $patientId",Toast.LENGTH_SHORT).show()

        antropometryFragment.idPatient = patientId
        biochemistryFragment.idPatient = patientId
        clinicFragment.idPatient = patientId
        dietaryFragment.idPatient = patientId
        diagnoseFragment.idPatient = patientId
        interenventionFragment.idPatient = patientId
        monevFragment.idPatient = patientId

        val drawerLayout: DrawerLayout = findViewById(R.id.vnut_drawer_layout)
        val navView: NavigationView = findViewById(R.id.vnut_nav_view)
        val navController = findNavController(R.id.vnut_nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration.Builder(
                R.id.vnut_navigation_antropometry, R.id.vnut_navigation_biochemistry,
                R.id.vnut_navigation_clinic, R.id.vnut_navigation_dietary,
                R.id.vnut_navigation_diagnose, R.id.vnut_navigation_interenvention,
                R.id.vnut_navigation_monev)
            .setDrawerLayout(drawerLayout)
            .build()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.vnut_navigation_antropometry, R.id.vnut_navigation_biochemistry,
                R.id.vnut_navigation_clinic, R.id.vnut_navigation_dietary,
                R.id.vnut_navigation_diagnose, R.id.vnut_navigation_interenvention,
                R.id.vnut_navigation_monev
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.vnut_nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
