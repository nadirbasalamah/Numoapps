package nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavArgument
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.nutritionist.ui.foodmenu.AddFoodMenuFragment
import nadirbasalamah.android.com.numoapps.nutritionist.ui.foodmenu.ViewFoodMenuFragment

class ViewNutritionRecordActivity : AppCompatActivity() {
    private var patientId: Int = 0
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var patientData: SharedPreferences
    companion object {
        const val PATIENT_ID = "PATIENT_ID"
        private lateinit var appContext: Context
        fun getAppContext(): Context {
            return appContext
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_nutrition_record)

        patientId = intent.getIntExtra(PATIENT_ID,0)

        appContext = applicationContext
        patientData = appContext.getSharedPreferences("Patient_Data", MODE_PRIVATE)
        val patientDataEdit = patientData.edit()
        patientDataEdit.putInt("id_patient",patientId)
        patientDataEdit.apply()

        Toast.makeText(applicationContext,"ID DARI ACTIVITY : $patientId",Toast.LENGTH_SHORT).show()

        val drawerLayout: DrawerLayout = findViewById(R.id.vnut_drawer_layout)
        val navView: NavigationView = findViewById(R.id.vnut_nav_view)
        val navController = findNavController(R.id.vnut_nav_host_fragment)

        navController.setGraph(R.navigation.view_nutrecord_navigation)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration.Builder(
                R.id.vnut_navigation_antropometry, R.id.vnut_navigation_biochemistry,
                R.id.vnut_navigation_clinic, R.id.vnut_navigation_dietary,
                R.id.vnut_navigation_diagnose, R.id.vnut_navigation_interenvention,
                R.id.vnut_navigation_monev,R.id.vnut_navigation_foodmenu)
            .setDrawerLayout(drawerLayout)
            .build()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.vnut_navigation_antropometry, R.id.vnut_navigation_biochemistry,
                R.id.vnut_navigation_clinic, R.id.vnut_navigation_dietary,
                R.id.vnut_navigation_diagnose, R.id.vnut_navigation_interenvention,
                R.id.vnut_navigation_monev,R.id.vnut_navigation_foodmenu
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.vnut_nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.patient_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_edit_nutrecord -> {
                val intent = Intent(applicationContext,AddNutritionRecordActivity::class.java)
                intent.putExtra(AddNutritionRecordActivity.PATIENT_ID,patientId)
                intent.putExtra(AddNutritionRecordActivity.MODE,"EDIT_MODE")
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}