package nadirbasalamah.android.com.numoapps.model

import com.google.gson.annotations.SerializedName

data class Patient(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("rm_number")
    private val rm_number: String,
    @SerializedName("rmgizi_number")
    private val rmgizi_number: String,
    @SerializedName("visitdate")
    private val visitdate: String,
    @SerializedName("referral")
    private val referral: String,
    @SerializedName("fullname")
    private val fullname: String,
    @SerializedName("age")
    private val age: Int,
    @SerializedName("gender")
    private val gender: String,
    @SerializedName("address")
    private val address: String,
    @SerializedName("phone_number")
    private val phone_number: String,
    @SerializedName("birthdate")
    private val birthdate: String,
    @SerializedName("education")
    private val education: String,
    @SerializedName("job")
    private val job: String,
    @SerializedName("religion")
    private val religion: String
)