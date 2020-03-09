package nadirbasalamah.android.com.numoapps.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Nutritionist(
    @SerializedName("id")
     val id: Int,
    @SerializedName("fullname")
     val fullname: String?,
    @SerializedName("username")
     val username: String?,
    @SerializedName("birthdate")
     val birthdate: String?,
    @SerializedName("gender")
     val gender: String?,
    @SerializedName("age")
     val age: Int?,
    @SerializedName("phone_number")
     val phone_number: String?,
    @SerializedName("email")
     val email: String?,
    @SerializedName("address")
     val address: String?,
    @SerializedName("nip")
     val nip: String?
): Parcelable