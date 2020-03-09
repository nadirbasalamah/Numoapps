package nadirbasalamah.android.com.numoapps.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.admin.AdminActivity
import nadirbasalamah.android.com.numoapps.nutritionist.NutritionistActivity
import nadirbasalamah.android.com.numoapps.patient.PatientActivity
import nadirbasalamah.android.com.numoapps.viewmodel.UserViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var loginData: SharedPreferences
    companion object {
        private lateinit var appContext: Context
        fun getAppContext(): Context {
            return appContext
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appContext = applicationContext

        btn_to_signup.setOnClickListener {
            val intent = Intent(applicationContext,RegisterActivity::class.java)
            startActivity(intent)
        }

        btn_to_forget_password.setOnClickListener {
            val intent = Intent(applicationContext,ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

        val enterListener =
            OnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_NULL
                    && event.action == KeyEvent.ACTION_DOWN
                ) {
                    login()
                }
                true
            }
        et_password_login.setOnEditorActionListener(enterListener)
        loginData = appContext.getSharedPreferences("Login", MODE_PRIVATE)
        val uname = loginData.getString("username",null).toString()
        Toast.makeText(applicationContext,"Username : " + uname,Toast.LENGTH_SHORT).show()
        checkRole(uname)

        btn_login.setOnClickListener{
            login()
        }
    }

    private fun login() {
        var data : HashMap<String, String> = HashMap<String, String> ()
        val username = et_username_login.text.toString()
        val password = et_password_login.text.toString()

        data.put("username",username)
        data.put("password",password)

        userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)
        userViewModel.setContext(applicationContext)
        userViewModel.login(data)?.observe(this, Observer {result ->
            if(result?.status == true) {
                val loginDataEdit = loginData.edit()
                loginDataEdit.putInt("id_user",result?.data.id)
                loginDataEdit.putString("username",result?.data.username)
                loginDataEdit.apply()
                checkRole(result?.data.username)
                et_username_login.setText("")
                et_password_login.setText("")
            }
        })
    }

    private fun checkRole(uname: String) {
        if(!uname.equals(null) ) {
            if(uname.equals("admin")) {
                startActivity(Intent(applicationContext,AdminActivity::class.java))
            } else if(uname.contains("AG_")) {
                startActivity(Intent(applicationContext,NutritionistActivity::class.java))
            } else {
                startActivity(Intent(applicationContext, PatientActivity::class.java))
            }
        }
    }
}
