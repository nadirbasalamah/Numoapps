package nadirbasalamah.android.com.numoapps.nutritionist.ui.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_nut_change_password.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.main.MainActivity
import nadirbasalamah.android.com.numoapps.viewmodel.AdminViewModel

class NutChangePasswordActivity : AppCompatActivity() {
    private lateinit var adminViewModel: AdminViewModel
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nut_change_password)

        val appContext = MainActivity.getAppContext()
        val loginData = appContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        userId = loginData.getInt("id_user",0)

        btn_edit_nut_save_password.setOnClickListener {
            val data: HashMap<String, String> = HashMap()
            val old_password = et_edit_nut_oldpassword.text.toString()
            val new_password = et_edit_nut_newpassword.text.toString()

            data.put("id",userId.toString())
            data.put("old_password",old_password)
            data.put("new_password",new_password)

            adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
            adminViewModel.setContext(applicationContext)
            adminViewModel.editNutritionistPassword(data)?.observe(this, Observer {result ->
                if(result?.status == true) {
                    finish()
                }
            })
        }
    }
}