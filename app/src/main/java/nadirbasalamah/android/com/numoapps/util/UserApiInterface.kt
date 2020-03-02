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
}