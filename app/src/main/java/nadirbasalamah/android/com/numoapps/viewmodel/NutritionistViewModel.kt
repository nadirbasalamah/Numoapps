package nadirbasalamah.android.com.numoapps.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nadirbasalamah.android.com.numoapps.model.response.*
import nadirbasalamah.android.com.numoapps.util.ApiClient
import nadirbasalamah.android.com.numoapps.util.NutritionistApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NutritionistViewModel : ViewModel() {
    private var nutritionistApiInterface: NutritionistApiInterface? = null
    private var postSetNutritionist: Call<PatientResponse?>? = null
    private var postUpdateAntropometry: Call<AntropometryResponse?>? = null
    private var postUpdateBiochemistry: Call<BiochemistryResponse?>? = null
    private var postUpdateClinic: Call<ClinicResponse?>? = null
    private var postUpdateDietary: Call<DietaryResponse?>? = null
    private var postUpdateDiagnose: Call<DiagnoseResponse?>? = null
    private var postUpdateInterenvention: Call<InterenventionResponse?>? = null
    private var postUpdateMonitoring: Call<MonitoringResponse?>? = null
    private var getNutRecordById: Call<NutritionRecordResponse?>? = null
    private var getFoodMenuById: Call<FoodMenuResponse?>? = null
    private var addFoodMenuById: Call<FoodMenuResponse?>? = null
    private var editFoodMenuId: Call<FoodMenuResponse?>? = null

    private var context: Context? = null

    internal fun setContext(context: Context?) { this.context = context }

    internal fun setNutritionist(data: HashMap<String, String>): MutableLiveData<PatientResponse?>? {
        nutritionistApiInterface = ApiClient.getClient()?.create(NutritionistApiInterface::class.java)
        var requestResult: PatientResponse?
        val result: MutableLiveData<PatientResponse?>? = MutableLiveData()
        postSetNutritionist = nutritionistApiInterface?.postSetNutritionist(data["id"]?.toInt(),data["id_nutritionist"]?.toInt())
        postSetNutritionist?.enqueue(
            object : Callback<PatientResponse?> {
                override fun onResponse(
                    call: Call<PatientResponse?>?,
                    response: Response<PatientResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        result?.value = requestResult
                        Toast.makeText(context, "Data pasien berhasil diubah!", Toast.LENGTH_SHORT).show()
                    } else {
                        result?.value = requestResult
                        Toast.makeText(context, "Perubahan data gagal!", Toast.LENGTH_SHORT).show()
                    }

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

    internal fun updateAntropometry(data: HashMap<String, String>): MutableLiveData<AntropometryResponse?>? {
        nutritionistApiInterface = ApiClient.getClient()?.create(NutritionistApiInterface::class.java)
        var requestResult: AntropometryResponse?
        val result: MutableLiveData<AntropometryResponse?>? = MutableLiveData()
        postUpdateAntropometry = nutritionistApiInterface?.postUpdateAntropometry(
            data["id"]?.toInt(),
            data["bb"]?.toFloat(),
            data["tb"]?.toFloat(),
            data["lila"]?.toFloat(),
            data["bbi"]?.toFloat(),
            data["fat"]?.toFloat(),
            data["visceral_fat"]?.toFloat(),
            data["muscle"]?.toFloat(),
            data["body_age"]?.toFloat()
        )
        postUpdateAntropometry?.enqueue(
            object : Callback<AntropometryResponse?> {
                override fun onResponse(
                    call: Call<AntropometryResponse?>?,
                    response: Response<AntropometryResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        result?.value = requestResult
                        Toast.makeText(context, "Data antropometri berhasil diubah!", Toast.LENGTH_SHORT).show()
                    } else {
                        result?.value = requestResult
                        Toast.makeText(context, "Perubahan data gagal!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(
                    call: Call<AntropometryResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun updateBiochemistry(data: HashMap<String, String>): MutableLiveData<BiochemistryResponse?>? {
        nutritionistApiInterface = ApiClient.getClient()?.create(NutritionistApiInterface::class.java)
        var requestResult: BiochemistryResponse?
        val result: MutableLiveData<BiochemistryResponse?>? = MutableLiveData()
        postUpdateBiochemistry = nutritionistApiInterface?.postUpdateBiochemistry(
            data["id"]?.toInt(),
            data["gda"]?.toFloat(),
            data["gdp"]?.toFloat(),
            data["gd2jpp"]?.toFloat(),
            data["asam_urat"]?.toFloat(),
            data["trigliserida"]?.toFloat(),
            data["kolesterol"]?.toFloat(),
            data["ldl"]?.toFloat(),
            data["hdl"]?.toFloat(),
            data["ureum"]?.toFloat(),
            data["kreatinin"]?.toFloat(),
            data["sgot"]?.toFloat(),
            data["sgpt"]?.toFloat()
        )
        postUpdateBiochemistry?.enqueue(
            object : Callback<BiochemistryResponse?> {
                override fun onResponse(
                    call: Call<BiochemistryResponse?>?,
                    response: Response<BiochemistryResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        result?.value = requestResult
                        Toast.makeText(context, "Data biokimia berhasil diubah!", Toast.LENGTH_SHORT).show()
                    } else {
                        result?.value = requestResult
                        Toast.makeText(context, "Perubahan data gagal!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(
                    call: Call<BiochemistryResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }
    internal fun updateClinic(data: HashMap<String, String>): MutableLiveData<ClinicResponse?>? {
        nutritionistApiInterface = ApiClient.getClient()?.create(NutritionistApiInterface::class.java)
        var requestResult: ClinicResponse?
        val result: MutableLiveData<ClinicResponse?>? = MutableLiveData()
        postUpdateClinic = nutritionistApiInterface?.postUpdateClinic(
            data["id"]?.toInt(),
            data["tensi"]?.toFloat(),
            data["rr"]?.toFloat(),
            data["suhu"]?.toFloat(),
            data["lainnya"],
            data["oedema"]?.toFloat(),
            data["aktivitas"],
            data["durasi_olahraga"],
            data["jenis_olahraga"],
            data["diagnosa_dahulu"],
            data["diagnosa_skrg"]
        )
        postUpdateClinic?.enqueue(
            object : Callback<ClinicResponse?> {
                override fun onResponse(
                    call: Call<ClinicResponse?>?,
                    response: Response<ClinicResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        result?.value = requestResult
                        Toast.makeText(context, "Data klinik berhasil diubah!", Toast.LENGTH_SHORT).show()
                    } else {
                        result?.value = requestResult
                        Toast.makeText(context, "Perubahan data gagal!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(
                    call: Call<ClinicResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun updateDietary(data: HashMap<String, String>): MutableLiveData<DietaryResponse?>? {
        nutritionistApiInterface = ApiClient.getClient()?.create(NutritionistApiInterface::class.java)
        var requestResult: DietaryResponse?
        val result: MutableLiveData<DietaryResponse?>? = MutableLiveData()
        postUpdateDietary = nutritionistApiInterface?.postUpdateDietary(
            data["id"]?.toInt(),
            data["nafsu_makan"],
            data["frekuensi_makan"],
            data["alergi"],
            data["makanan_kesukaan"],
            data["dietary_nasi"],
            data["dietary_lauk_hewani"],
            data["dietary_lauk_nabati"],
            data["dietary_sayur"],
            data["dietary_sumber_minyak"],
            data["dietary_minuman"],
            data["dietary_softdrink"],
            data["dietary_jus"],
            data["dietary_suplemen"],
            data["dietary_lainnya"],
            data["lain_lain"]
        )
        postUpdateDietary?.enqueue(
            object : Callback<DietaryResponse?> {
                override fun onResponse(
                    call: Call<DietaryResponse?>?,
                    response: Response<DietaryResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        result?.value = requestResult
                        Toast.makeText(context, "Data dietary berhasil diubah!", Toast.LENGTH_SHORT).show()
                    } else {
                        result?.value = requestResult
                        Toast.makeText(context, "Perubahan data gagal!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(
                    call: Call<DietaryResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun updateDiagnose(data: HashMap<String, String>): MutableLiveData<DiagnoseResponse?>? {
        nutritionistApiInterface = ApiClient.getClient()?.create(NutritionistApiInterface::class.java)
        var requestResult: DiagnoseResponse?
        val result: MutableLiveData<DiagnoseResponse?>? = MutableLiveData()
        postUpdateDiagnose = nutritionistApiInterface?.postUpdateDiagnose(
            data["id"]?.toInt(),
            data["diagnose"]
        )
        postUpdateDiagnose?.enqueue(
            object : Callback<DiagnoseResponse?> {
                override fun onResponse(
                    call: Call<DiagnoseResponse?>?,
                    response: Response<DiagnoseResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        result?.value = requestResult
                        Toast.makeText(context, "Data diagnosis berhasil diubah!", Toast.LENGTH_SHORT).show()
                    } else {
                        result?.value = requestResult
                        Toast.makeText(context, "Perubahan data gagal!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(
                    call: Call<DiagnoseResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun updateInterenvention(data: HashMap<String, String>): MutableLiveData<InterenventionResponse?>? {
        nutritionistApiInterface = ApiClient.getClient()?.create(NutritionistApiInterface::class.java)
        var requestResult: InterenventionResponse?
        val result: MutableLiveData<InterenventionResponse?>? = MutableLiveData()
        postUpdateInterenvention = nutritionistApiInterface?.postUpdateInterenvention(
            data["id"]?.toInt(),
            data["energi"]?.toFloat(),
            data["keterangan_inter"],
            data["persen_karbohidrat"]?.toFloat(),
            data["persen_protein"]?.toFloat(),
            data["persen_lemak"]?.toFloat()
        )
        postUpdateInterenvention?.enqueue(
            object : Callback<InterenventionResponse?> {
                override fun onResponse(
                    call: Call<InterenventionResponse?>?,
                    response: Response<InterenventionResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        result?.value = requestResult
                        Toast.makeText(context, "Data interenvensi berhasil diubah!", Toast.LENGTH_SHORT).show()
                    } else {
                        result?.value = requestResult
                        Toast.makeText(context, "Perubahan data gagal!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(
                    call: Call<InterenventionResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun updateMonitoring(data: HashMap<String, String>): MutableLiveData<MonitoringResponse?>? {
        nutritionistApiInterface = ApiClient.getClient()?.create(NutritionistApiInterface::class.java)
        var requestResult: MonitoringResponse?
        val result: MutableLiveData<MonitoringResponse?>? = MutableLiveData()
        postUpdateMonitoring = nutritionistApiInterface?.postUpdateMonitoring(
            data["id"]?.toInt(),
            data["mon_date"],
            data["result"],
            data["return_date"]
        )
        postUpdateMonitoring?.enqueue(
            object : Callback<MonitoringResponse?> {
                override fun onResponse(
                    call: Call<MonitoringResponse?>?,
                    response: Response<MonitoringResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        result?.value = requestResult
                        Toast.makeText(context, "Data monitoring berhasil diubah!", Toast.LENGTH_SHORT).show()
                    } else {
                        result?.value = requestResult
                        Toast.makeText(context, "Perubahan data gagal!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(
                    call: Call<MonitoringResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun getNutRecordById(data: Int?): MutableLiveData<NutritionRecordResponse?>? {
        nutritionistApiInterface = ApiClient.getClient()?.create(NutritionistApiInterface::class.java)
        var requestResult: NutritionRecordResponse?
        val result: MutableLiveData<NutritionRecordResponse?>? = MutableLiveData()
        getNutRecordById = nutritionistApiInterface?.getNutRecordById(data)
        getNutRecordById?.enqueue(
            object : Callback<NutritionRecordResponse?> {
                override fun onResponse(
                    call: Call<NutritionRecordResponse?>?,
                    response: Response<NutritionRecordResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        result?.value = requestResult
                        Toast.makeText(context, "Data gizi berhasil ditemukan!", Toast.LENGTH_SHORT).show()
                    } else {
                        result?.value = requestResult
                        Toast.makeText(context, "Data gizi tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }

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

    internal fun getFoodMenuById(data: Int?): MutableLiveData<FoodMenuResponse?>? {
        nutritionistApiInterface = ApiClient.getClient()?.create(NutritionistApiInterface::class.java)
        var requestResult: FoodMenuResponse?
        val result: MutableLiveData<FoodMenuResponse?>? = MutableLiveData()
        getFoodMenuById = nutritionistApiInterface?.getFoodMenuById(data)
        getFoodMenuById?.enqueue(
            object : Callback<FoodMenuResponse?> {
                override fun onResponse(
                    call: Call<FoodMenuResponse?>?,
                    response: Response<FoodMenuResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        result?.value = requestResult
                        Toast.makeText(context, "Data menu makanan berhasil ditemukan!", Toast.LENGTH_SHORT).show()
                    } else {
                        result?.value = requestResult
                        Toast.makeText(context, "Data menu makanan tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(
                    call: Call<FoodMenuResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun addFoodMenu(data: HashMap<String, String>): MutableLiveData<FoodMenuResponse?>? {
        nutritionistApiInterface = ApiClient.getClient()?.create(NutritionistApiInterface::class.java)
        var requestResult: FoodMenuResponse?
        val result: MutableLiveData<FoodMenuResponse?>? = MutableLiveData()
        addFoodMenuById = nutritionistApiInterface?.postAddFoodMenu(
            data["id"]?.toInt(),
            data["breakfast"],
            data["breakfast_time"],
            data["lunch"],
            data["lunch_time"],
            data["dinner"],
            data["dinner_time"]
        )
        addFoodMenuById?.enqueue(
            object : Callback<FoodMenuResponse?> {
                override fun onResponse(
                    call: Call<FoodMenuResponse?>?,
                    response: Response<FoodMenuResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        result?.value = requestResult
                        Toast.makeText(context, "Data menu makanan berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                    } else {
                        result?.value = requestResult
                        Toast.makeText(context, "Penambahan data gagal!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(
                    call: Call<FoodMenuResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun editFoodMenu(data: HashMap<String, String>): MutableLiveData<FoodMenuResponse?>? {
        nutritionistApiInterface = ApiClient.getClient()?.create(NutritionistApiInterface::class.java)
        var requestResult: FoodMenuResponse?
        val result: MutableLiveData<FoodMenuResponse?>? = MutableLiveData()
        editFoodMenuId = nutritionistApiInterface?.postEditFoodMenu(
            data["id"]?.toInt(),
            data["breakfast"],
            data["breakfast_time"],
            data["lunch"],
            data["lunch_time"],
            data["dinner"],
            data["dinner_time"]
        )
        editFoodMenuId?.enqueue(
            object : Callback<FoodMenuResponse?> {
                override fun onResponse(
                    call: Call<FoodMenuResponse?>?,
                    response: Response<FoodMenuResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        result?.value = requestResult
                        Toast.makeText(context, "Data menu makanan berhasil diubah!", Toast.LENGTH_SHORT).show()
                    } else {
                        result?.value = requestResult
                        Toast.makeText(context, "Perubahan data gagal!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(
                    call: Call<FoodMenuResponse?>,
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