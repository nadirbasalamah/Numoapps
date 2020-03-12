package nadirbasalamah.android.com.numoapps.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodMenu(
    @SerializedName("id")
    val id: Int,
    @SerializedName("id_patient")
    val id_patient: Int,
    @SerializedName("breakfast")
    val breakfast: String?,
    @SerializedName("breakfast_time")
    val breakfast_time: String?,
    @SerializedName("lunch")
    val lunch: String?,
    @SerializedName("lunch_time")
    val lunch_time: String?,
    @SerializedName("dinner")
    val dinner: String?,
    @SerializedName("dinner_time")
    val dinner_time: String?
): Parcelable