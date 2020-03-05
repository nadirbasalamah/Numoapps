package nadirbasalamah.android.com.numoapps.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_forget_password.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.viewmodel.UserViewModel

class ForgetPasswordActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        btn_forget_password.setOnClickListener {
            var data : HashMap<String, String> = HashMap<String, String> ()
            val username = et_forget_username.text.toString()
            val password = et_forget_password.text.toString()

            data.put("username",username)
            data.put("password",password)

            userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)
            userViewModel.setContext(applicationContext)
            userViewModel.forgetPassword(data)?.observe(this, Observer { result ->
                if(result?.status == true) {
                    finish()
                }
            })
        }
    }
}