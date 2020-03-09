package nadirbasalamah.android.com.numoapps.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Patient(
    @SerializedName("id")
     val id: Int,
    @SerializedName("rm_number")
     val rm_number: String?,
    @SerializedName("rmgizi_number")
     val rmgizi_number: String?,
    @SerializedName("visitdate")
     val visitdate: String?,
    @SerializedName("referral")
     val referral: String?,
    @SerializedName("fullname")
     val fullname: String?,
    @SerializedName("age")
     val age: Int?,
    @SerializedName("gender")
     val gender: String?,
    @SerializedName("address")
     val address: String?,
    @SerializedName("phone_number")
     val phone_number: String?,
    @SerializedName("birthdate")
     val birthdate: String?,
    @SerializedName("education")
     val education: String?,
    @SerializedName("job")
     val job: String?,
    @SerializedName("religion")
     val religion: String?
): Parcelable