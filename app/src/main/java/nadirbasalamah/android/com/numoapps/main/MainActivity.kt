package nadirbasalamah.android.com.numoapps.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.model.UserResponse
import nadirbasalamah.android.com.numoapps.util.ApiClient
import nadirbasalamah.android.com.numoapps.util.UserApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private var userApiInterface: UserApiInterface? = null
    private var postLogin: Call<UserResponse?>? = null
    private lateinit var username: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userApiInterface = ApiClient.create()

        btn_login.setOnClickListener{
            username = et_username_login.text.toString()
            password = et_password_login.text.toString()
            postLogin = userApiInterface?.postLogin(username,password)
            postLogin?.enqueue(
                object : Callback<UserResponse?> {
                    override fun onResponse(
                        call: Call<UserResponse?>?,
                        response: Response<UserResponse?>?
                    ) {
                        var test = response?.body().toString()
                        if(test.contains("true",false)) {
                            Toast.makeText(applicationContext, "Login success!",Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(applicationContext, "Login failed!",Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(
                        call: Call<UserResponse?>,
                        t: Throwable
                    ) {
                        Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
                        Log.d("ERROR:",t.toString())
                    }
                }
            )
        }
    }
}
