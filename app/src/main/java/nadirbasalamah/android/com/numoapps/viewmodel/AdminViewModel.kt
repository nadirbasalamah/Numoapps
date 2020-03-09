package nadirbasalamah.android.com.numoapps.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nadirbasalamah.android.com.numoapps.model.response.*
import nadirbasalamah.android.com.numoapps.util.AdminApiInterface
import nadirbasalamah.android.com.numoapps.util.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminViewModel :  ViewModel() {
    private var adminApiInterface: AdminApiInterface? = null
    private var getAllPatients: Call<PatientsResponse?>? = null
    private var getPatientById: Call<PatientResponse?>? = null
    private var postDeletePatient: Call<PatientResponse?>? = null
    private var postEditPatient: Call<PatientResponse?>? = null
    private var postAddPatient: Call<PatientResponse?>? = null
    private var getPatientByName: Call<PatientsResponse?>? = null

    private var getAllNutritionists: Call<NutritionistsResponse?>? = null
    private var getNutritionistById: Call<NutritionistResponse?>? = null
    private var postDeleteNutritionist: Call<NutritionistResponse?>? = null
    private var postEditNutritionist: Call<NutritionistResponse?>? = null
    private var postEditNutritionistPassword: Call<NutritionistResponse?>? = null
    private var postAddNutritionist: Call<NutritionistResponse?>? = null
    private var getNutritionistByName: Call<NutritionistsResponse?>? = null

    private var context: Context? = null

    internal fun setContext(context: Context?) { this.context = context }

    internal fun getAllPatients(): MutableLiveData<PatientsResponse?>? {
        adminApiInterface = ApiClient.getClient()?.create(AdminApiInterface::class.java)
        var requestResult: PatientsResponse?
        val result: MutableLiveData<PatientsResponse?>? = MutableLiveData()
        getAllPatients = adminApiInterface?.getAllPatients()
        getAllPatients?.enqueue(
            object : Callback<PatientsResponse?> {
                override fun onResponse(
                    call: Call<PatientsResponse?>?,
                    response: Response<PatientsResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data pasien ditemukan!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Data pasien tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<PatientsResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun getPatientById(data: Int): MutableLiveData<PatientResponse?>? {
        adminApiInterface = ApiClient.getClient()?.create(AdminApiInterface::class.java)
        var requestResult: PatientResponse?
        val result: MutableLiveData<PatientResponse?>? = MutableLiveData()
        getPatientById = adminApiInterface?.getPatientById(data)
        getPatientById?.enqueue(
            object : Callback<PatientResponse?> {
                override fun onResponse(
                    call: Call<PatientResponse?>?,
                    response: Response<PatientResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data pasien ditemukan!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Data pasien tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<PatientResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun deletePatient(data: Int): MutableLiveData<PatientResponse?>? {
        adminApiInterface = ApiClient.getClient()?.create(AdminApiInterface::class.java)
        var requestResult: PatientResponse?
        val result: MutableLiveData<PatientResponse?>? = MutableLiveData()
        postDeletePatient = adminApiInterface?.deletePatientById(data)
        postDeletePatient?.enqueue(
            object : Callback<PatientResponse?> {
                override fun onResponse(
                    call: Call<PatientResponse?>?,
                    response: Response<PatientResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data pasien berhasil dihapus!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Penghapusan data pasien gagal, pasien tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<PatientResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun editPatient(data: HashMap<String, String>): MutableLiveData<PatientResponse?>? {
        adminApiInterface = ApiClient.getClient()?.create(AdminApiInterface::class.java)
        var requestResult: PatientResponse?
        val result: MutableLiveData<PatientResponse?>? = MutableLiveData()
        postEditPatient = adminApiInterface?.postEditPatient(
            data["id"]?.toInt(),
            data["address"],
            data["phone_number"],
            data["education"],
            data["job"],
            data["religion"]
        )
        postEditPatient?.enqueue(
            object : Callback<PatientResponse?> {
                override fun onResponse(
                    call: Call<PatientResponse?>?,
                    response: Response<PatientResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data pasien berhasil diubah!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Perubahan data pasien gagal, pasien tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<PatientResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun addPatient(data: HashMap<String, String>): MutableLiveData<PatientResponse?>? {
        adminApiInterface = ApiClient.getClient()?.create(AdminApiInterface::class.java)
        var requestResult: PatientResponse?
        val result: MutableLiveData<PatientResponse?>? = MutableLiveData()
        postAddPatient = adminApiInterface?.postAddPatient(
            data["rm_number"],
            data["rmgizi_number"],
            data["visitdate"],
            data["referral"],
            data["fullname"],
            data["age"]?.toInt(),
            data["gender"],
            data["address"],
            data["phone_number"],
            data["birthdate"],
            data["education"],
            data["job"],
            data["religion"]
        )
        postAddPatient?.enqueue(
            object : Callback<PatientResponse?> {
                override fun onResponse(
                    call: Call<PatientResponse?>?,
                    response: Response<PatientResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data pasien berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Penambahan data pasien gagal!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<PatientResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun getPatientByName(data: String): MutableLiveData<PatientsResponse?>? {
        adminApiInterface = ApiClient.getClient()?.create(AdminApiInterface::class.java)
        var requestResult: PatientsResponse?
        val result: MutableLiveData<PatientsResponse?>? = MutableLiveData()
        getPatientByName = adminApiInterface?.getPatientByName(data)
        getPatientByName?.enqueue(
            object : Callback<PatientsResponse?> {
                override fun onResponse(
                    call: Call<PatientsResponse?>?,
                    response: Response<PatientsResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data pasien berhasil ditemukan!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Data pasien tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<PatientsResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun getAllNutritionists(): MutableLiveData<NutritionistsResponse?>? {
        adminApiInterface = ApiClient.getClient()?.create(AdminApiInterface::class.java)
        var requestResult: NutritionistsResponse?
        val result: MutableLiveData<NutritionistsResponse?>? = MutableLiveData()
        getAllNutritionists = adminApiInterface?.getAllNutritionists()
        getAllNutritionists?.enqueue(
            object : Callback<NutritionistsResponse?> {
                override fun onResponse(
                    call: Call<NutritionistsResponse?>?,
                    response: Response<NutritionistsResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data ahli gizi berhasil ditemukan!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Data ahli gizi tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<NutritionistsResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun getNutritionistById(data: Int): MutableLiveData<NutritionistResponse?>? {
        adminApiInterface = ApiClient.getClient()?.create(AdminApiInterface::class.java)
        var requestResult: NutritionistResponse?
        val result: MutableLiveData<NutritionistResponse?>? = MutableLiveData()
        getNutritionistById = adminApiInterface?.getNutritionistById(data)
        getNutritionistById?.enqueue(
            object : Callback<NutritionistResponse?> {
                override fun onResponse(
                    call: Call<NutritionistResponse?>?,
                    response: Response<NutritionistResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data ahli gizi berhasil ditemukan!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Data ahli gizi tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<NutritionistResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun deleteNutritionist(data: Int): MutableLiveData<NutritionistResponse?>? {
        adminApiInterface = ApiClient.getClient()?.create(AdminApiInterface::class.java)
        var requestResult: NutritionistResponse?
        val result: MutableLiveData<NutritionistResponse?>? = MutableLiveData()
        postDeleteNutritionist = adminApiInterface?.deleteNutritionistById(data)
        postDeleteNutritionist?.enqueue(
            object : Callback<NutritionistResponse?> {
                override fun onResponse(
                    call: Call<NutritionistResponse?>?,
                    response: Response<NutritionistResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data ahli gizi berhasil dihapus!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Penghapusan data ahli gizi gagal, data ahli gizi tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<NutritionistResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun editNutritionist(data: HashMap<String, String>): MutableLiveData<NutritionistResponse?>? {
        adminApiInterface = ApiClient.getClient()?.create(AdminApiInterface::class.java)
        var requestResult: NutritionistResponse?
        val result: MutableLiveData<NutritionistResponse?>? = MutableLiveData()
        postEditNutritionist = adminApiInterface?.postEditNutritionist(
            data["id"]?.toInt(),
            data["username"],
            data["phone_number"],
            data["email"],
            data["address"]
        )
        postEditNutritionist?.enqueue(
            object : Callback<NutritionistResponse?> {
                override fun onResponse(
                    call: Call<NutritionistResponse?>?,
                    response: Response<NutritionistResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data ahli gizi berhasil diubah!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Perubahan data ahli gizi gagal, Data ahli gizi tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<NutritionistResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun editNutritionistPassword(data: HashMap<String, String>): MutableLiveData<NutritionistResponse?>? {
        adminApiInterface = ApiClient.getClient()?.create(AdminApiInterface::class.java)
        var requestResult: NutritionistResponse?
        val result: MutableLiveData<NutritionistResponse?>? = MutableLiveData()
        postEditNutritionistPassword = adminApiInterface?.postEditNutritionistPassword(
            data["id"]?.toInt(),
            data["old_password"],
            data["new_password"]
        )
        postEditNutritionistPassword?.enqueue(
            object : Callback<NutritionistResponse?> {
                override fun onResponse(
                    call: Call<NutritionistResponse?>?,
                    response: Response<NutritionistResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data password ahli gizi berhasil diubah!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Perubahan data password ahli gizi gagal, Data ahli gizi tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<NutritionistResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun addNutritionist(data: HashMap<String, String>): MutableLiveData<NutritionistResponse?>? {
        adminApiInterface = ApiClient.getClient()?.create(AdminApiInterface::class.java)
        var requestResult: NutritionistResponse?
        val result: MutableLiveData<NutritionistResponse?>? = MutableLiveData()
        postAddNutritionist = adminApiInterface?.postAddNutritionist(
            data["fullname"],
            data["username"],
            data["password"],
            data["birthdate"],
            data["gender"],
            data["age"]?.toInt(),
            data["phone_number"],
            data["email"],
            data["address"],
            data["nip"]
        )
        postAddNutritionist?.enqueue(
            object : Callback<NutritionistResponse?> {
                override fun onResponse(
                    call: Call<NutritionistResponse?>?,
                    response: Response<NutritionistResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data ahli gizi berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Penambahan data ahli gizi gagal!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<NutritionistResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun getNutritionistByName(data: String): MutableLiveData<NutritionistsResponse?>? {
        adminApiInterface = ApiClient.getClient()?.create(AdminApiInterface::class.java)
        var requestResult: NutritionistsResponse?
        val result: MutableLiveData<NutritionistsResponse?>? = MutableLiveData()
        getNutritionistByName = adminApiInterface?.getNutritionistByName(data)
        getNutritionistByName?.enqueue(
            object : Callback<NutritionistsResponse?> {
                override fun onResponse(
                    call: Call<NutritionistsResponse?>?,
                    response: Response<NutritionistsResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data ahli gizi berhasil ditemukan!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Data ahli gizi tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<NutritionistsResponse?>,
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