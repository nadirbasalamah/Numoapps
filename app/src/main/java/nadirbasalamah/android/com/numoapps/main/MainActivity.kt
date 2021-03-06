package nadirbasalamah.android.com.numoapps.main

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
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

        btn_login_info.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage(R.string.login_info)
                .setPositiveButton(R.string.confirmation_yes
                ) { dialog, _ ->
                    dialog.dismiss()
                }
            // Create the AlertDialog object and return it
            builder.create()
            builder.show()
        }

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
        val isLoggedIn = loginData.getBoolean("isLoggedIn",false)
        checkRole(uname,isLoggedIn)

        btn_login.setOnClickListener{
            login()
        }
    }

    private fun login() {
        var data : HashMap<String, String> = HashMap()
        val username = et_username_login.text.toString()
        val password = et_password_login.text.toString()

        data.put("username",username)
        data.put("password",password)

        userViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)
        userViewModel.setContext(applicationContext)
        userViewModel.login(data)?.observe(this, Observer {result ->
            if(result?.status == true) {
                loginData = appContext.getSharedPreferences("Login",MODE_PRIVATE)
                val loginDataEdit = loginData.edit()
                loginDataEdit.putInt("id_user",result.data.id)
                loginDataEdit.putBoolean("isLoggedIn",true)
                if(username.contains("AG_")) {
                    loginDataEdit.putString("username","AG_" + result.data.username)
                    checkRole("AG_" + result.data.username,true)
                } else {
                    loginDataEdit.putString("username",result.data.username)
                    checkRole(result.data.username,true)
                }
                loginDataEdit.apply()
                et_username_login.setText("")
                et_password_login.setText("")
            }
        })
    }

    private fun checkRole(uname: String, isLoggedIn: Boolean) {
        if(!uname.equals(null)  && isLoggedIn) {
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
