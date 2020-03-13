package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.nutritionist.ui.foodmenu.AddFoodMenuFragment

class AddNutritionRecordActivity : AppCompatActivity() {
    private var patientId: Int = 0
    private lateinit var appBarConfiguration: AppBarConfiguration
    companion object {
        const val ADD_MODE = "ADD_MODE"
        const val PATIENT_ID = "PATIENT_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_nutrition_record)

        patientId = intent.getIntExtra(PATIENT_ID,0)

        val bundle = Bundle()
        bundle.putInt("IDPATIENT",patientId)
        bundle.putString("MODE", ADD_MODE)

        val drawerLayout: DrawerLayout = findViewById(R.id.enut_drawer_layout)
        val navView: NavigationView = findViewById(R.id.enut_nav_view)
        val navController = findNavController(R.id.enut_nav_host_fragment)
        navController.setGraph(R.navigation.edit_nutrecord_navigation,bundle)

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
