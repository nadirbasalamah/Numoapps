package nadirbasalamah.android.com.numoapps.util

import nadirbasalamah.android.com.numoapps.model.UserResponse
import retrofit2.http.FormUrlEncoded
import retrofit2.http.*
import retrofit2.Call

interface AdminApiInterface {

    @GET("patients")
    fun getAllPatients(): Call<UserResponse?>?

    @GET("patient/{id}")
    fun getPatientById(@Path("id") id: Int?): Call<UserResponse?>?


    @POST("deletePatient/{id}")
    fun deletePatientById(@Path("id") id: Int?): Call<UserResponse?>?

    @FormUrlEncoded
    @POST("editPatient/{id}")
    fun postEditPatient(
        @Path("id") id: Int?,
        @Field("address") address: String?,
        @Field("phone_number") phone_number: String?,
        @Field("education") education: String?,
        @Field("job") job: String?,
        @Field("religion") religion: String?
    ): Call<UserResponse?>?

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
    ): Call<UserResponse?>?

    @GET("getPatient/{fullname}")
    fun getPatientByName(@Path("fullname") fullname: String?): Call<UserResponse?>?

    @GET("nutritionists")
    fun getAllNutritionists(): Call<UserResponse?>?

    @GET("nutritionist/{id}")
    fun getNutritionistById(@Path("id") id: Int?): Call<UserResponse?>?

    @POST("deleteNutritionist/{id}")
    fun deleteNutritionistById(@Path("id") id: Int?): Call<UserResponse?>?

    @FormUrlEncoded
    @POST("editNutritionist/{id}")
    fun postEditNutritionist(
        @Path("id") id: Int?,
        @Field("username") username: String?,
        @Field("phone_number") phone_number: String?,
        @Field("email") email: String?,
        @Field("address") address: String?
    ): Call<UserResponse?>?

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
    ): Call<UserResponse?>?

    @GET("getNutritionist/{fullname}")
    fun getNutritionistByName(@Path("fullname") fullname: String?)


}