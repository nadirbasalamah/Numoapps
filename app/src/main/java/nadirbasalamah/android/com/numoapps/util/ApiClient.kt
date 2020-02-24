package nadirbasalamah.android.com.numoapps.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
     fun create(): UserApiInterface {
         val retrofit = Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .baseUrl("http://192.168.1.12:8080/klinikapps-api/public/")
             .build()
         return retrofit.create(UserApiInterface::class.java)
     }
}