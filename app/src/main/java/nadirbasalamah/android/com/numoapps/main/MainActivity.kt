package nadirbasalamah.android.com.numoapps.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.model.UserResponse
import nadirbasalamah.android.com.numoapps.util.ApiClient
import nadirbasalamah.android.com.numoapps.util.UserApiInterface
import nadirbasalamah.android.com.numoapps.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_to_signup.setOnClickListener {
            val intent = Intent(applicationContext,RegisterActivity::class.java)
            startActivity(intent)
        }

        btn_to_forget_password.setOnClickListener {
            val intent = Intent(applicationContext,ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener{
            var data : HashMap<String, String> = HashMap<String, String> ()
            val username = et_username_login.text.toString()
            val password = et_password_login.text.toString()

            data.put("username",username)
            data.put("password",password)

            userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)
            userViewModel.setContext(applicationContext)
            userViewModel.login(data)

        }
    }
}
