package nadirbasalamah.android.com.numoapps.user

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_edit_profile.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.main.MainActivity
import nadirbasalamah.android.com.numoapps.viewmodel.UserViewModel
import java.util.HashMap

class EditProfileActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val appContext = MainActivity.getAppContext()
        val loginData = appContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        userId = loginData.getInt("id_user",0)

        userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)
        userViewModel.setContext(applicationContext)
        userViewModel.getUserById(userId)?.observe(this, Observer {result ->
            if(result?.status == true) {
                et_edit_username.setText(result.data.username)
                et_edit_phone_number.setText(result.data.phone_number)
                et_edit_email.setText(result.data.email)
                et_edit_address.setText(result.data.address)
            }
        })

        btn_edit_save.setOnClickListener {
            val data : HashMap<String, String> = HashMap<String, String> ()
            val username = et_edit_username.text.toString()
            val phone_number = et_edit_phone_number.text.toString()
            val email = et_edit_email.text.toString()
            val address = et_edit_address.text.toString()

            data.put("id",userId.toString())
            data.put("username",username)
            data.put("phone_number",phone_number)
            data.put("email",email)
            data.put("address",address)

            userViewModel.editProfile(data)?.observe(this, Observer {result ->
                if(result?.status == true) {
                    finish()
                }
            })
        }

        btn_to_change_password.setOnClickListener {
            val intent = Intent(applicationContext,ChangePasswordActivity::class.java)
            startActivity(intent)
        }
    }
}
