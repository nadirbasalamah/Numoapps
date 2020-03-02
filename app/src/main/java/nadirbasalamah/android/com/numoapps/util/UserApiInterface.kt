package nadirbasalamah.android.com.numoapps.util

import nadirbasalamah.android.com.numoapps.model.UserResponse
import retrofit2.http.*
import retrofit2.Call


interface UserApiInterface {

    @FormUrlEncoded
    @POST("registerUser")
    fun postRegister(
        @Field("fullname") fullname: String?,
        @Field("username") username: String?,
        @Field("password") password: String?,
        @Field("birthdate") birthdate: String?,
        @Field("gender") gender: String?,
        @Field("age") age: Int?,
        @Field("phone_number") phone_number: String?,
        @Field("email") email: String?,
        @Field("address") address: String?,
        @Field("id_number") id_number: String?,
        @Field("id_type") id_type: String?
    ): Call<UserResponse?>?

    @FormUrlEncoded
    @POST("loginUser")
    fun postLogin(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<UserResponse?>?

    @FormUrlEncoded
    @POST("forgetPassword")
    fun postForgetPassword(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<UserResponse?>?

    @FormUrlEncoded
    @POST("editProfile/{id}")
    fun postEditProfile(
        @Path("id") id: Int?,
        @Field("username") username: String?,
        @Field("phone_number") phone_number: String?,
        @Field("email") email: String?,
        @Field("address") address: String?
    ): Call<UserResponse?>?

    @FormUrlEncoded
    @POST("changePassword/{id}")
    fun postChangePassword(
        @Path("id") id: Int?,
        @Field("old_password") old_password: String?,
        @Field("new_password") new_password: String?
    ) : Call<UserResponse?>?

    @GET("user/{id}")
    fun getUserById(@Path("id") id: Int?) : Call<UserResponse?>?

    @GET("userNutRecord/{id}")
    fun getNutRecordById(@Path("id") id: Int?) : Call<UserResponse?>?
}