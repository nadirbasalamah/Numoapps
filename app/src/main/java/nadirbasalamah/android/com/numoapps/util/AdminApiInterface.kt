package nadirbasalamah.android.com.numoapps.util

import nadirbasalamah.android.com.numoapps.model.response.*
import retrofit2.http.FormUrlEncoded
import retrofit2.http.*
import retrofit2.Call

interface AdminApiInterface {

    @GET("patients")
    fun getAllPatients(): Call<PatientsResponse?>?

    @GET("patient/{id}")
    fun getPatientById(@Path("id") id: Int?): Call<PatientResponse?>?


    @POST("deletePatient/{id}")
    fun deletePatientById(@Path("id") id: Int?): Call<PatientResponse?>?

    @FormUrlEncoded
    @POST("editPatient/{id}")
    fun postEditPatient(
        @Path("id") id: Int?,
        @Field("address") address: String?,
        @Field("phone_number") phone_number: String?,
        @Field("education") education: String?,
        @Field("job") job: String?,
        @Field("religion") religion: String?
    ): Call<PatientResponse?>?

    @FormUrlEncoded
    @POST("addPatient")
    fun postAddPatient(
        @Field("rm_number") rm_number: String?,
        @Field("rmgizi_number") rmgizi_number: String?,
        @Field("visitdate") visitdate: String?,
        @Field("referral") referral: String?,
        @Field("fullname") fullname: String?,
        @Field("age") age: Int?,
        @Field("gender") gender: String?,
        @Field("address") address: String?,
        @Field("phone_number") phone_number: String?,
        @Field("birthdate") birthdate: String?,
        @Field("education") education: String?,
        @Field("job") job: String?,
        @Field("religion") religion: String?
    ): Call<PatientResponse?>?

    @GET("getPatient/{fullname}")
    fun getPatientByName(@Path("fullname") fullname: String?): Call<PatientsResponse?>?

    @GET("nutritionists")
    fun getAllNutritionists(): Call<NutritionistsResponse?>?

    @GET("nutritionist/{id}")
    fun getNutritionistById(@Path("id") id: Int?): Call<NutritionistResponse?>?

    @POST("deleteNutritionist/{id}")
    fun deleteNutritionistById(@Path("id") id: Int?): Call<NutritionistResponse?>?

    @FormUrlEncoded
    @POST("editNutritionist/{id}")
    fun postEditNutritionist(
        @Path("id") id: Int?,
        @Field("username") username: String?,
        @Field("phone_number") phone_number: String?,
        @Field("email") email: String?,
        @Field("address") address: String?
    ): Call<NutritionistResponse?>?

    @FormUrlEncoded
    @POST("editNutPassword/{id}")
    fun postEditNutritionistPassword(
        @Path("id") id: Int?,
        @Field("old_password") old_password: String?,
        @Field("new_password") new_password: String?
    ): Call<NutritionistResponse?>?

    @FormUrlEncoded
    @POST("addNutritionist")
    fun postAddNutritionist(
        @Field("fullname") fullname: String?,
        @Field("username") username: String?,
        @Field("password") password: String?,
        @Field("birthdate") birthdate: String?,
        @Field("gender") gender: String?,
        @Field("age") age: Int?,
        @Field("phone_number") phone_number: String?,
        @Field("email") email: String?,
        @Field("address") address: String?,
        @Field("nip") nip: String?
    ): Call<NutritionistResponse?>?

    @GET("getNutritionist/{fullname}")
    fun getNutritionistByName(@Path("fullname") fullname: String?): Call<NutritionistsResponse?>?


}