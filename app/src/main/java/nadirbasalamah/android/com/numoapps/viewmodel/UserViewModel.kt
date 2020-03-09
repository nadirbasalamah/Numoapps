package nadirbasalamah.android.com.numoapps.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nadirbasalamah.android.com.numoapps.model.response.NutritionRecordResponse
import nadirbasalamah.android.com.numoapps.model.response.UserResponse
import nadirbasalamah.android.com.numoapps.util.ApiClient
import nadirbasalamah.android.com.numoapps.util.UserApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    private var userApiInterface: UserApiInterface? = null
    private var postLogin: Call<UserResponse?>? = null
    private var postRegister: Call<UserResponse?>? = null
    private var postForgetPassword: Call<UserResponse?>? = null
    private var postEditProfile: Call<UserResponse?>? = null
    private var postChangePassword: Call<UserResponse?>? = null
    private var getUserById: Call<UserResponse?>? = null
    private var getNutRecordByUserId: Call<NutritionRecordResponse?>? = null

    private var context: Context? = null

    internal fun setContext(context: Context?) { this.context = context }

    internal fun register(data: HashMap<String, String>): MutableLiveData<UserResponse?>? {
        userApiInterface = ApiClient.getClient()?.create(UserApiInterface::class.java)
        var requestResult: UserResponse?
        val result: MutableLiveData<UserResponse?>? = MutableLiveData()
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
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Register sukses!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Register gagal!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
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
        return result
    }

    internal fun login(data: HashMap<String, String>): MutableLiveData<UserResponse?>? {
        userApiInterface = ApiClient.getClient()?.create(UserApiInterface::class.java)
        var requestResult: UserResponse?
        val result: MutableLiveData<UserResponse?>? = MutableLiveData()
        postLogin = userApiInterface?.postLogin(data["username"],data["password"])
        postLogin?.enqueue(
            object : Callback<UserResponse?> {
                override fun onResponse(
                    call: Call<UserResponse?>?,
                    response: Response<UserResponse?>?
                ) {
                    var test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Login sukses!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Login gagal!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
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
        return result
    }

    internal fun editProfile(data: HashMap<String, String>): MutableLiveData<UserResponse?>? {
        userApiInterface = ApiClient.getClient()?.create(UserApiInterface::class.java)
        var requestResult: UserResponse?
        val result: MutableLiveData<UserResponse?>? = MutableLiveData()
        postEditProfile = userApiInterface?.postEditProfile(
            data["id"]?.toInt(),
            data["username"],
            data["phone_number"],
            data["email"],
            data["address"]
        )
        postEditProfile?.enqueue(
            object : Callback<UserResponse?> {
                override fun onResponse(
                    call: Call<UserResponse?>?,
                    response: Response<UserResponse?>?
                ) {
                    var test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data profil berhasil diubah!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Perubahan data profil gagal!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
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
        return result
    }

    internal fun forgetPassword(data: HashMap<String, String>): MutableLiveData<UserResponse?>? {
        userApiInterface = ApiClient.getClient()?.create(UserApiInterface::class.java)
        var requestResult: UserResponse?
        val result: MutableLiveData<UserResponse?>? = MutableLiveData()
        postForgetPassword = userApiInterface?.postForgetPassword(data["username"],data["password"])
        postForgetPassword?.enqueue(
            object : Callback<UserResponse?> {
                override fun onResponse(
                    call: Call<UserResponse?>?,
                    response: Response<UserResponse?>?
                ) {
                    var test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Password berhasil diubah!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Perubahan password gagal, username tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
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
        return result
    }

    internal fun changePassword(data: HashMap<String, String>): MutableLiveData<UserResponse?>? {
        userApiInterface = ApiClient.getClient()?.create(UserApiInterface::class.java)
        var requestResult: UserResponse?
        val result: MutableLiveData<UserResponse?>? = MutableLiveData()
        postChangePassword = userApiInterface?.postChangePassword(
            data["id"]?.toInt(),
            data["old_password"],
            data["new_password"]
        )
        postChangePassword?.enqueue(
            object : Callback<UserResponse?> {
                override fun onResponse(
                    call: Call<UserResponse?>?,
                    response: Response<UserResponse?>?
                ) {
                    var test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Password berhasil diubah!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Perubahan password gagal!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
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
        return result
    }

    internal fun getUserById(data: Int): MutableLiveData<UserResponse?>? {
        userApiInterface = ApiClient.getClient()?.create(UserApiInterface::class.java)
        var requestResult: UserResponse?
        val result: MutableLiveData<UserResponse?>? = MutableLiveData()
        getUserById = userApiInterface?.getUserById(data)
        getUserById?.enqueue(
            object : Callback<UserResponse?> {
                override fun onResponse(
                    call: Call<UserResponse?>?,
                    response: Response<UserResponse?>?
                ) {
                    var test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data pengguna berhasil ditemukan!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Data pengguna tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
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
        return result
    }

    internal fun getNutRecordByUserId(data: Int): MutableLiveData<NutritionRecordResponse?>? {
        userApiInterface = ApiClient.getClient()?.create(UserApiInterface::class.java)
        var requestResult: NutritionRecordResponse?
        val result: MutableLiveData<NutritionRecordResponse?>? = MutableLiveData()
        getNutRecordByUserId = userApiInterface?.getNutRecordById(data)
        getNutRecordByUserId?.enqueue(
            object : Callback<NutritionRecordResponse?> {
                override fun onResponse(
                    call: Call<NutritionRecordResponse?>?,
                    response: Response<NutritionRecordResponse?>?
                ) {
                    var test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data gizi berhasil ditemukan!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Data gizi tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<NutritionRecordResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }
}