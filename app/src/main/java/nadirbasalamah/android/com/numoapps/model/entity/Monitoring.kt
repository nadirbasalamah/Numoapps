package nadirbasalamah.android.com.numoapps.model.entity

import com.google.gson.annotations.SerializedName

data class Monitoring(
    @SerializedName("id")
    val id: Int,
    @SerializedName("id_patient")
    val id_patient: Int,
    @SerializedName("mon_date")
    val mon_date: String,
    @SerializedName("result")
    val result: String,
    @SerializedName("return_date")
    var return_date: String?
)