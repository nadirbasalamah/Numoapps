package nadirbasalamah.android.com.numoapps.util

import nadirbasalamah.android.com.numoapps.model.UserResponse
import retrofit2.http.*
import retrofit2.Call


interface UserApiInterface {

    @FormUrlEncoded
    @POST("loginUser")
    fun postLogin(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<UserResponse?>?
}