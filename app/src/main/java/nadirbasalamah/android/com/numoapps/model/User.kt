package nadirbasalamah.android.com.numoapps.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("role")
    private val role: String,
    @SerializedName("fullname")
    private val fullname: String,
    @SerializedName("username")
    private val username: String,
    @SerializedName("birthdate")
    private val birthdate: String,
    @SerializedName("gender")
    private val gender: String,
    @SerializedName("age")
    private val age: Int,
    @SerializedName("phone_number")
    private val phone_number: String,
    @SerializedName("email")
    private val email: String,
    @SerializedName("address")
    private val address: String,
    @SerializedName("id_number")
    private val id_number: String,
    @SerializedName("id_type")
    private val id_type: String
)