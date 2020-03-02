package nadirbasalamah.android.com.numoapps.model

import com.google.gson.annotations.SerializedName

data class Monitoring(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("id_patient")
    private val id_patient: Int,
    @SerializedName("mon_date")
    private val mon_date: String,
    @SerializedName("result")
    private val result: String
)