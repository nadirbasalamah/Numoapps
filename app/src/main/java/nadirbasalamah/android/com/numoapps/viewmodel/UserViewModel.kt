package nadirbasalamah.android.com.numoapps.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import nadirbasalamah.android.com.numoapps.model.UserResponse
import nadirbasalamah.android.com.numoapps.util.ApiClient
import nadirbasalamah.android.com.numoapps.util.UserApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    private var userApiInterface: UserApiInterface? = null
    private var postLogin: Call<UserResponse?>? = null
    private var postRegister: Call<UserResponse?>? = null
    private var context: Context? = null

    internal fun setContext(context: Context?) { this.context = context }

    internal fun register(data: HashMap<String, String>) {
        userApiInterface = ApiClient.getClient()?.create(UserApiInterface::class.java)
        postRegister = userApiInterface?.postRegister(
            data["fullname"],
            data["username"],
            data["password"],
            data["birthdate"],
            data["gender"],
            data["age"]?.toInt(),
            data["phone_number"],
            data["email"],
            data["address"],
            data["id_number"],
            data["id_type"]
        )
        postRegister?.enqueue(
            object : Callback<UserResponse?> {
                override fun onResponse(
                    call: Call<UserResponse?>?,
                    response: Response<UserResponse?>?
                ) {
                    var test = response?.body().toString()
                    if(test.contains("true",false)) {
                        Toast.makeText(context, "Register sukses!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Register gagal!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(
                    call: Call<UserResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
    }

    internal fun login(data: HashMap<String, String>) {
        userApiInterface = ApiClient.getClient()?.create(UserApiInterface::class.java)
        postLogin = userApiInterface?.postLogin(data["username"],data["password"])
        postLogin?.enqueue(
            object : Callback<UserResponse?> {
                override fun onResponse(
                    call: Call<UserResponse?>?,
                    response: Response<UserResponse?>?
                ) {
                    var test = response?.body().toString()
                    if(test.contains("true",false)) {
                        Toast.makeText(context, "Login sukses!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Login gagal!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(
                    call: Call<UserResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
    }

}