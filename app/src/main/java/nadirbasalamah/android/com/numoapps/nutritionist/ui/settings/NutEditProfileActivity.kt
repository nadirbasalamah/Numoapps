package nadirbasalamah.android.com.numoapps.nutritionist.ui.settings

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.rizmaulana.sheenvalidator.lib.SheenValidator
import kotlinx.android.synthetic.main.activity_nut_edit_profile.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.main.MainActivity
import nadirbasalamah.android.com.numoapps.viewmodel.AdminViewModel

class NutEditProfileActivity : AppCompatActivity() {
    private lateinit var adminViewModel: AdminViewModel
    private lateinit var sheenValidator: SheenValidator
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nut_edit_profile)

        val appContext = MainActivity.getAppContext()
        val loginData = appContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        userId = loginData.getInt("id_user",0)

        adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
        adminViewModel.setContext(applicationContext)
        adminViewModel.getNutritionistById(userId)?.observe(this, Observer {result ->
            if(result?.status == true) {
                et_nut_edit_username.setText(result.data.username)
                et_nut_edit_phone_number.setText(result.data.phone_number)
                et_nut_edit_email.setText(result.data.email)
                et_nut_edit_address.setText(result.data.address)
            }
        })

        sheenValidator = SheenValidator(this)
        sheenValidator.setOnValidatorListener {
            Toast.makeText(this,"Validasi sukses!",Toast.LENGTH_SHORT).show()
        }
        sheenValidator.registerAsRequired(et_nut_edit_username)
        sheenValidator.registerAsRequired(et_nut_edit_phone_number)
        sheenValidator.registerAsRequired(et_nut_edit_email)
        sheenValidator.registerAsRequired(et_nut_edit_address)

        btn_nut_edit_save.setOnClickListener {
            sheenValidator.validate()
            val data: HashMap<String, String> = HashMap()
            val username = et_nut_edit_username.text.toString()
            val phone_number = et_nut_edit_phone_number.text.toString()
            val email = et_nut_edit_email.text.toString()
            val address = et_nut_edit_address.text.toString()

            data.put("id",userId.toString())
            data.put("username",username)
            data.put("phone_number",phone_number)
            data.put("email",email)
            data.put("address",address)

            adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
            adminViewModel.setContext(applicationContext)
            adminViewModel.editNutritionist(data)?.observe(this, Observer {result ->
                if(result?.status == true) {
                    finish()
                }
            })
        }

        btn_to_nut_change_password.setOnClickListener {
            val intent = Intent(this,NutChangePasswordActivity::class.java)
            startActivity(intent)
        }
    }
}