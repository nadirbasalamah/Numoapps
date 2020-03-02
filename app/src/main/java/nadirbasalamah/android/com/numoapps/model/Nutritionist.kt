package nadirbasalamah.android.com.numoapps.model

import com.google.gson.annotations.SerializedName

data class Nutritionist(
    @SerializedName("id")
    private val id: Int,
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
    @SerializedName("nip")
    private val nip: String
)