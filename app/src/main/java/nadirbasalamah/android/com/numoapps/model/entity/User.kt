package nadirbasalamah.android.com.numoapps.model.entity

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
     val id: Int,
    @SerializedName("role")
     val role: String,
    @SerializedName("fullname")
     val fullname: String,
    @SerializedName("username")
     val username: String,
    @SerializedName("birthdate")
     val birthdate: String,
    @SerializedName("gender")
     val gender: String,
    @SerializedName("age")
     val age: Int,
    @SerializedName("phone_number")
     val phone_number: String,
    @SerializedName("email")
     val email: String,
    @SerializedName("address")
     val address: String,
    @SerializedName("id_number")
     val id_number: String,
    @SerializedName("id_type")
     val id_type: String
)