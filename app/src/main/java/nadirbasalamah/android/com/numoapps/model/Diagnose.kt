package nadirbasalamah.android.com.numoapps.model

import com.google.gson.annotations.SerializedName

data class Diagnose(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("id_patient")
    private val id_patient: Int,
    @SerializedName("diagnose")
    private val diagnose: String
)